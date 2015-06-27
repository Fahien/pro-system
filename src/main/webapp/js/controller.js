
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

	view.producer = new ProducerFactory;
}

angular.module('proApp.controller').controller('FormatCtrl', ['FormatFactory', formatController]);

function formatController(FormatFactory) {
	var view = this;
	view.formats = [];
	FormatFactory.query({}, function (formatFactory) {
		view.formats = formatFactory;
	});

	view.format = new FormatFactory;
}

angular.module('proApp.controller').controller('OrderCtrl', ['OrderFactory', orderController]);

function orderController(OrderFactory) {
	var view = this;
	view.orders = [];
	OrderFactory.query({}, function (orderFactory) {
		view.orders = orderFactory;
	});

	view.order = new OrderFactory;
	view.order.date = Date.now();
	view.order.$save();
}