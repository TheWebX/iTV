'use strict';

var storeApp = angular.module('AngularStore', []);

storeApp.factory("DataService", function () {
    var myStore = new Store();

    var myCart = new ShoppingCart("AngularStore");
    
    myCart.addCheckoutParameters("PayPal", "sales@coderforge.org");
    
    return {
        store: myStore,
        cart: myCart
    };
});

storeApp.config(['$routeProvider', function($routeProvider) {
	  $routeProvider.
	      when('/store', {
	        templateUrl: 'components/store.htm',
	        controller: StoreController 
	      }).
	      when('/cart', {
	        templateUrl: 'components/shoppingCart.htm',
	        controller: StoreController
	      }).
	      otherwise({
	        redirectTo: '/store'
	      });
}]);