
angular.module('proApp.controller', []);

angular.module('proApp.controller').controller('ProductCtrl', ['ProductFactory', productController]);

function productController(ProductFactory) {
	var view = this;
	view.products = [];
	ProductFactory.query({}, function (productFactory) {
		view.products = productFactory;
	});

	view.product = new ProductFactory;
}

angular.module('proApp.controller').controller('ProducerCtrl', ['ProducerFactory', producerController]);

function producerController(ProducerFactory) {
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