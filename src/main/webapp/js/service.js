angular.module('proApp.service', ['ngResource']);

angular.module('proApp.service').factory('ProductFactory', productFactory);

function productFactory($resource) {
	return $resource('/rs/product/', {}, {})
}

angular.module('proApp.service').factory('ProducerFactory', producerFactory);

function producerFactory($resource) {
	return $resource('/rs/producer/', {}, {})
}

angular.module('proApp.service').factory('FormatFactory', formatFactory);

function formatFactory($resource) {
	return $resource('/rs/format/', {}, {})
}