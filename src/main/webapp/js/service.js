angular.module('proApp.service', ['ngResource']);

angular.module('proApp.service').factory('ProductFactory', productFactory);

function productFactory($resource) {
	return $resource('/rs/product/:id', {}, {})
}

angular.module('proApp.service').factory('ProducerFactory', producerFactory);

function producerFactory($resource) {
	return $resource('/rs/producer/:id', {}, {})
}

angular.module('proApp.service').factory('FormatFactory', formatFactory);

function formatFactory($resource) {
	return $resource('/rs/format/:id', {}, {})
}

angular.module('proApp.service').factory('OrderFactory', orderFactory);

function orderFactory($resource) {
	return $resource('/rs/order/:id', {}, {})
}