
angular.module('proApp.controller', []);

angular.module('proApp.controller').controller('ProductCtrl', ['$routeParams', 'ProductFactory', productController]);

function productController($routeParams, ProductFactory) {
	var view = this;
	view.products = [];
	ProductFactory.query({}, function (productFactory) {
		view.products = productFactory;
	});

	if ($routeParams.id) {
		view.product = new ProductFactory.get({id:$routeParams.id});
	}
}

angular.module('proApp.controller').controller('ProducerCtrl', ['$routeParams', 'ProducerFactory', producerController]);

function producerController($routeParams, ProducerFactory) {
	var view = this;
	view.producers = [];
	ProducerFactory.query({}, function (producerFactory) {
		view.producers = producerFactory;
	});

	if ($routeParams.id) {
		view.producer = new ProducerFactory.get({id:$routeParams.id});
	}
}

angular.module('proApp.controller').controller('FormatCtrl', ['$routeParams', 'FormatFactory', formatController]);

function formatController($routeParams, FormatFactory) {
	var view = this;
	view.formats = [];
	FormatFactory.query({}, function (formatFactory) {
		view.formats = formatFactory;
	});

	if ($routeParams.id) {
		view.format = new FormatFactory.get({id:$routeParams.id});
	}
}

angular.module('proApp.controller').controller('OrderCtrl', ['$routeParams', 'OrderFactory', orderController]);

function orderController($routeParams, OrderFactory) {
	var view = this;
	view.orders = [];
	OrderFactory.query({}, function (orderFactory) {
		view.orders = orderFactory;
	});

	if ($routeParams.id) {
		view.order = new OrderFactory.get({id:$routeParams.id});
	}
	else {
		view.order = new OrderFactory;
		view.order.date = Date.now();
	}
}