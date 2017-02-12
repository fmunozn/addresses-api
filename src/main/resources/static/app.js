'use strict';

var addressApp = angular.module('addressApp', ['ngRoute']);


addressApp.config(['$routeProvider','$locationProvider',
                   function($routeProvider, $locationProvider) {
	$routeProvider
	.when('/address', {
		templateUrl: 'templates/address.html',
		controller: 'AddressController'
	})
	.otherwise({
		redirectTo: 'address'
	});
}]);