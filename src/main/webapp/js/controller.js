
angular.module('proApp.controller', []);

angular.module('proApp.controller').controller('ProductCtrl', ['$scope', 'ProductFactory', productController]);

function productController($scope, ProductFactory) {
	var view = this;
	view.products = [];
	ProductFactory.query({}, function (productFactory) {
		view.products = productFactory;
	});

	view.product = new ProductFactory;
}

angular.module('proApp.controller').controller('ProducerCtrl', ['$scope', 'ProducerFactory', producerController]);

function producerController($scope, ProducerFactory) {
	var view = this;
	view.producers = [];
	ProducerFactory.query({}, function (producerFactory) {
		view.producers = producerFactory;
	});

	view.producer = new ProducerFactory;
}