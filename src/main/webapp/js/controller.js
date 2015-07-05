
angular.module('proApp.controller', []);

angular.module('proApp.controller').controller('HomeCtrl', [homeController]);

function homeController() {
	var view = this;
}

angular.module('proApp.controller').controller('ProductCtrl', ['$location', '$routeParams', 'ProductFactory', 'ProducerFactory', productController]);

function productController($location, $routeParams, ProductFactory, ProducerFactory) {
	var view = this;
	view.error = "";

	view.products = [];
	ProductFactory.query({}, function (productFactory) {
		view.products = productFactory;
	});
	
	if($location.path() == "/product/create" || $location.path().indexOf("/product/edit") == 0) {
		view.producers = [];
		ProducerFactory.query({}, function (producerFactory) {
			view.producers = producerFactory;
		});
	}

	view.create = create;
	function create(product) {
		product.$save({},
			function(p) {
				$location.path("/product/" + p.id); 
			},
			function(e) {
				view.error = e.data;
			}
		);
	}
	
	view.update = update;
	function update(product) {
		product.$update({},
			function(p) {
				$location.path("/product/" + p.id); 
			},
			function(e) {
				view.error = e.data;
			}
		);
	}
	
	view.del = del;
	function del(product) {
		product.$delete({id:product.id}, function() {
			$location.path("/product/"); 
		});
	}

	view.product = new ProductFactory;
	if ($routeParams.id) {
		view.product = new ProductFactory.get({id:$routeParams.id});
	}
}

angular.module('proApp.controller').controller('ProducerCtrl', ['$location', '$routeParams', 'ProducerFactory', producerController]);

function producerController($location, $routeParams, ProducerFactory) {
	var view = this;
	view.error = "";

	view.producers = [];
	ProducerFactory.query({}, function (producerFactory) {
		view.producers = producerFactory;
	});

	view.producer = new ProducerFactory;
	if ($routeParams.id) {
		view.producer = new ProducerFactory.get({id:$routeParams.id});
	}
	
	view.create = create;
	function create(producer) {
		producer.$save({},
			function(p) {
				$location.path("/producer/" + p.id); 
			},
			function(e) {
				view.error = e.data;
			}
		);
	}

	view.update = update;
	function update(producer) {
		producer.$update({},
			function(p) {
				$location.path("/producer/" + p.id); 
			},
			function(e) {
				view.error = e.data;
			}
		);
	}

	view.del = del;
	function del(producer) {
		producer.$delete({id:producer.id}, function() {
			$location.path("/producer/"); 
		});
	}
}

angular.module('proApp.controller').controller('FormatCtrl', ['$location', '$routeParams', 'FormatFactory', formatController]);

function formatController($location, $routeParams, FormatFactory) {
	var view = this;
	view.formats = [];
	FormatFactory.query({}, function (formatFactory) {
		view.formats = formatFactory;
	});

	view.format = new FormatFactory
	if ($routeParams.id) {
		view.format = new FormatFactory.get({id:$routeParams.id});
	}

	view.create = create;
	function create(format) {
		format.$save({},
			function(f) {
				$location.path("/format/"); 
			},
			function(e) {
				view.error = e.data;
			}
		);
	}

	view.del = del;
	function del(format) {
		format.$delete({id:format.id}, function() {
			FormatFactory.query({}, function (formatFactory) {
				view.formats = formatFactory;
			});
		});
	}
}

angular.module('proApp.controller').controller('OrderCtrl', ['$location', '$routeParams', 'OrderFactory', 'NamedProductFactory', orderController]);

function orderController($location, $routeParams, OrderFactory, ProductFactory) {
	var view = this;
	view.error = "";

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

	view.create = create;
	function create(order) {
		order.$save({}, function(o) {
			$location.path("/order/edit/" + o.id); 
		})
	}

	view.update = update;
	function update(order) {
		order.$update({},
			function(o) {
				$location.path("/order/" + o.id); 
			},
			function(e) {
				view.error = e.data;
			}
		);
	}
	
	view.reset = reset;
	function reset() {
		view.order.products = [];
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

	view.del = del;
	function del(order) {
		order.$delete({id:order.id}, function() {
			$location.path("/order/");
		});
	}
}