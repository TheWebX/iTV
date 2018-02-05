'use strict';

function StoreController($scope, $routeParams, $http, DataService) {

	$scope.store = DataService.store;
	$scope.cart = DataService.cart;
	$scope.cart.$scope = $scope;
	$scope.cart.$http = $http;
	$scope.currency = "GBP";
	
	$http({
		url : '/products/findAll',
		method : 'GET'
	}).then(function($scope, $http, data, status, headers, config) {
		$scope.products = data.data;
		var updatePromotions = function($scope, $http) {
			$http({
				url : '/promotions/findAll',
				method : 'GET'
			}).then(function($scope, data, status, headers, config) {
				$scope.store.promotions = data.data;
			}.bind(this, $scope));
		}.bind(this, $scope, $http);
		updatePromotions();
	}.bind(this, $scope, $http));
	
	if ($routeParams.productSku != null) {
		$scope.product = $scope.store.getProduct($routeParams.productSku);
	}
	
}
