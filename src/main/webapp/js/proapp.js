'use strict';

angular.module('proApp', ['proApp.service','proApp.controller','ngRoute']);

angular.module('proApp').config(configRoute);

function configRoute($routeProvider, $httpProvider) {
	$routeProvider.when('/', {templateUrl: 'view/home/home.html', controller: 'HomeCtrl', controllerAs: 'view'});
	
	$routeProvider.when('/product', {templateUrl: 'view/product/products.html', controller: 'ProductCtrl', controllerAs: 'view'});
	$routeProvider.when('/product/create', {templateUrl: 'view/product/create.html', controller: 'ProductCtrl', controllerAs: 'view'});
	$routeProvider.when('/product/:id', { templateUrl: 'view/product/product.html', controller: 'ProductCtrl', controllerAs: 'view'});
	$routeProvider.when('/product/edit/:id', { templateUrl: 'view/product/edit.html', controller: 'ProductCtrl', controllerAs: 'view'});
	
	$routeProvider.when('/producer', {templateUrl: 'view/producer/producers.html', controller: 'ProducerCtrl', controllerAs: 'view'});
	$routeProvider.when('/producer/create', {templateUrl: 'view/producer/create.html', controller: 'ProducerCtrl', controllerAs: 'view'});
	$routeProvider.when('/producer/:id', { templateUrl: 'view/producer/producer.html', controller: 'ProducerCtrl', controllerAs: 'view'});
	$routeProvider.when('/producer/edit/:id', { templateUrl: 'view/producer/edit.html', controller: 'ProducerCtrl', controllerAs: 'view'});
	
	$routeProvider.when('/format', {templateUrl: 'view/format/formats.html', controller: 'FormatCtrl', controllerAs: 'view'});
	$routeProvider.when('/format/create', {templateUrl: 'view/format/create.html', controller: 'FormatCtrl', controllerAs: 'view'});
	$routeProvider.when('/format/:id', { templateUrl: 'view/format/format.html', controller: 'FormatCtrl', controllerAs: 'view'});
	$routeProvider.when('/format/edit/:id', { templateUrl: 'view/format/edit.html', controller: 'FormatCtrl', controllerAs: 'view'});
	
	$routeProvider.when('/order', {templateUrl: 'view/order/orders.html', controller: 'OrderCtrl', controllerAs: 'view'});
	$routeProvider.when('/order/create', {templateUrl: 'view/order/create.html', controller: 'OrderCtrl', controllerAs: 'view'});
	$routeProvider.when('/order/:id', { templateUrl: 'view/order/order.html', controller: 'OrderCtrl', controllerAs: 'view'});
	$routeProvider.when('/order/edit/:id', { templateUrl: 'view/order/edit.html', controller: 'OrderCtrl', controllerAs: 'view'});
	
	$routeProvider.otherwise({redirectTo: '/'});

	$httpProvider.defaults.useXDomain = true;
	delete $httpProvider.defaults.headers.common['X-Requested-With'];
}
