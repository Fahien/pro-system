'use strict';

angular.module('proApp', ['proApp.service','proApp.controller','ngRoute']);

angular.module('proApp').config(configRoute);

function configRoute($routeProvider, $httpProvider) {
	$routeProvider.when('/product', {templateUrl: 'view/product/product.html', controller: 'ProductCtrl', controllerAs: 'view'});
	$routeProvider.when('/product/create', {templateUrl: 'view/product/create.html', controller: 'ProductCtrl', controllerAs: 'view'});
	$routeProvider.when('/producer', {templateUrl: 'view/producer/producer.html', controller: 'ProducerCtrl', controllerAs: 'view'});
	$routeProvider.when('/producer/create', {templateUrl: 'view/producer/create.html', controller: 'ProducerCtrl', controllerAs: 'view'});
	$routeProvider.otherwise({redirectTo: '/'});

	$httpProvider.defaults.useXDomain = true;
	delete $httpProvider.defaults.headers.common['X-Requested-With'];
}
