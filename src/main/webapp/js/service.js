angular.module('proApp.service', ['ngResource']);

angular.module('proApp.service').factory('ProductFactory', productFactory);

function productFactory($resource) {
	return $resource('/rs/product/', {}, {})
}