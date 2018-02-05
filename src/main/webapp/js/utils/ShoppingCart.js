
function ShoppingCart(cartName) {
    this.cartName = cartName;
    this.clearCart = false;
    this.checkoutParameters = {};
    this.items = [];
    this.loadItems();
    this.$http = null;
    this.$scope = null;
    
    var self = this;
    $(window).unload(function () {
        if (self.clearCart) {
            self.clearItems();
        }
        self.saveItems();
        self.clearCart = false;
    });
}

ShoppingCart.prototype.loadItems = function () {
    var items = localStorage != null ? localStorage[this.cartName + "_items"] : null;
    if (items != null && JSON != null) {
        try {
            var items = JSON.parse(items);
            for (var i = 0; i < items.length; i++) {
                var item = items[i];
                if (item.sku != null && item.name != null && item.price != null && item.quantity != null) {
                    item = new CartItem(item.sku, item.name, item.price, item.quantity);
                    this.items.push(item);
                }
            }
        }
        catch (err) {
        	console.log("Errors on Loading: " + err);
        }
    }
}

ShoppingCart.prototype.saveItems = function () {
    if (localStorage != null && JSON != null) {
        localStorage[this.cartName + "_items"] = JSON.stringify(this.items);
    }
}

ShoppingCart.prototype.addItem = function (sku, name, price, quantity) {
    quantity = this.toNumber(quantity);
    if (quantity != 0) {
        var found = false;
        for (var i = 0; i < this.items.length && !found; i++) {
            var item = this.items[i];
            if (item.sku == sku) {
                found = true;
                item.quantity = this.toNumber(item.quantity + quantity);
                if (item.quantity <= 0) {
                    this.items.splice(i, 1);
                }
            }
        }
        if (!found) {
            var item = new CartItem(sku, name, price, quantity);
            this.items.push(item);
        }
        this.saveItems();
        this.updateTotal();
    }
}

ShoppingCart.prototype.updateTotal = function () {
	var productIds = [];
    for (var i = 0; i < this.items.length; i++) {
        var item = this.items[i];
        for (var quantity = 0; quantity < item.quantity; quantity++) {
            productIds.push(item.sku);
        }
    }
	this.$http({
		url : '/checkout/generateQuote',
		method : 'POST',             
	    data: productIds,
	    headers: {
	        'Content-Type': 'application/json'
	    }
	}).then(function($scope, data, status, headers, config) {
		$scope.cartTotal = data.data;
	}.bind(this, this.$scope));
	
}

ShoppingCart.prototype.getTotalCount = function (sku) {
    var count = 0;
    for (var i = 0; i < this.items.length; i++) {
        var item = this.items[i];
        if (sku == null || item.sku == sku) {
            count += this.toNumber(item.quantity);
        }
    }
    return count;
}

ShoppingCart.prototype.clearItems = function () {
    this.items = [];
    this.saveItems();
    this.updateTotal();
}

ShoppingCart.prototype.addCheckoutParameters = function (serviceName, merchantID, options) {
	
    if (serviceName != "PayPal") {
        throw "serviceName must be 'PayPal'.";
    }
    if (merchantID == null) {
        throw "A merchantID is required in order to checkout.";
    }

    this.checkoutParameters[serviceName] = new CheckoutParameters(serviceName, merchantID, options);
}

ShoppingCart.prototype.checkout = function (serviceName, clearCart) {
	
    if (serviceName == null) {
        var p = this.checkoutParameters[Object.keys(this.checkoutParameters)[0]];
        serviceName = p.serviceName;
    }

    if (serviceName == null) {
        throw "Use the 'addCheckoutParameters' method to define at least one checkout service.";
    }

    var parms = this.checkoutParameters[serviceName];
    if (parms == null) {
        throw "Cannot get checkout parameters for '" + serviceName + "'.";
    }
    switch (parms.serviceName) {
        case "PayPal":
            this.checkoutPayPal(parms, clearCart);
            break;
        default:
            throw "Unknown checkout service: " + parms.serviceName;
    }
}

ShoppingCart.prototype.checkoutPayPal = function (parms, clearCart) {

    // global data
    var data = {
        cmd: "_cart",
        business: parms.merchantID,
        upload: "1",
        rm: "2",
        charset: "utf-8",
        discount_amount_cart: this.$scope.cartTotal.totalDiscountAmount,
        currency_code: "GBP",
        image_url: "https://raw.githubusercontent.com/dario-avella/iTV/master/src/main/webapp/img/paypal-coderforge.png"
    };

    // item data
    for (var i = 0; i < this.items.length; i++) {
        var item = this.items[i];
        var ctr = i + 1;
        data["item_number_" + ctr] = item.sku;
        data["item_name_" + ctr] = item.name;
        data["quantity_" + ctr] = item.quantity;
        data["amount_" + ctr] = item.price.toFixed(2);
    }

    // build form
    var form = $('<form/></form>');
    form.attr("action", "https://www.paypal.com/cgi-bin/webscr");
    form.attr("method", "POST");
    form.attr("image_url", "https://raw.githubusercontent.com/dario-avella/iTV/master/src/main/webapp/img/paypal-coderforge.png");
    this.addFormFields(form, data);
    this.addFormFields(form, parms.options);
    $("body").append(form);

    // submit form
    this.clearCart = clearCart == null || clearCart;
    form.submit();
    form.remove();
}

ShoppingCart.prototype.addFormFields = function (form, data) {
    if (data != null) {
        $.each(data, function (name, value) {
            if (value != null) {
                var input = $("<input></input>").attr("type", "hidden").attr("name", name).val(value);
                form.append(input);
            }
        });
    }
}
ShoppingCart.prototype.toNumber = function (value) {
    value = value * 1;
    return isNaN(value) ? 0 : value;
}

