
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

angular.module('proApp.controller').controller('OrderCtrl', ['$routeParams', 'OrderFactory', 'NamedProductFactory', orderController]);

function orderController($routeParams, OrderFactory, ProductFactory) {
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
		view.order.products = [];
	}
	
	view.products = [];
	view.productName = "";
	view.search = search;
	function search(productName) {
		ProductFactory.query({name:productName}, function(productFactory) {
			view.products = productFactory;
		});
	}
	
	view.add = add;
	function add(product) {
		var matches = view.order.products.filter(function(containedProduct) {
			if (containedProduct.id == product.id && containedProduct.format.value == product.format.value) {
				containedProduct.format = product.format;
				containedProduct.number = product.number;
				return true;
			}
			return false;
		});
		if (!matches.length) {
			view.order.products.push(product);
		}
	}
}