
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