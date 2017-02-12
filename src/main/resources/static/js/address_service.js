'use strict';

angular.module('addressApp').factory('AddressService', ['$http', '$q', function($http, $q){

	var ADDRESS_SERVICE_URL = '/address/ie/';
	var ADDRESS_GEO_SERVICE_URL = '/addressgeo/ie/';
	var ADDRESS_REVERSE_GEO_URL = '/rgeo/ie/'
		
		
	var REST_SERVICE_URI = '/address/ie//53.332067/-6.255492?distance=50&format=json';

	console.log('Starting Address Service');
	
	var factory = {
			searchAddress: searchAddress
	};

	return factory;

	function searchAddress(searchAddress) {

		console.log('Service Requesting Search Address: '+searchAddress);
		
		var deferred = $q.defer();
		$http.get(ADDRESS_SERVICE_URL+searchAddress+"?lines=3")
		.then(
				function (response) {
					deferred.resolve(response.data);
				},
				function(errResponse){
					console.error('Error retrieving addresses');
					deferred.reject(errResponse);
				}
		);
		return deferred.promise;

	}
	
	function searchAddressGeo(searchAddress) {

		console.log('Service Requesting Search Address with Geo Information: '+searchAddress);
		
		var deferred = $q.defer();
		$http.get(ADDRESS_GEO_SERVICE_URL+searchAddress+"?lines=3")
		.then(
				function (response) {
					deferred.resolve(response.data);
				},
				function(errResponse){
					console.error('Error retrieving addresses');
					deferred.reject(errResponse);
				}
		);
		return deferred.promise;

	}
	
	function searchReverseGeo(longitude,latitude,distance) {

		console.log('Service Requesting Search Address with Geo Information: '+searchAddress);
		
		var deferred = $q.defer();
		$http.get(ADDRESS_REVERSE_GEO_URL+"/"+latitude+"/"+longigude+"?distance="+distance)
		.then(
				function (response) {
					deferred.resolve(response.data);
				},
				function(errResponse){
					console.error('Error retrieving addresses');
					deferred.reject(errResponse);
				}
		);
		return deferred.promise;

	}

}]);