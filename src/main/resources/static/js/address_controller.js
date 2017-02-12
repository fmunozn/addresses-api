'use strict';

/*angular.module('addressApp')
.controller('AddressController', ['$scope', 'AddressService', function($scope, AddressService) {*/

angular.module('addressApp')
.controller('AddressController', ['$scope', 'AddressService', function($scope, AddressService) {

	console.log('Address Controller Init');

	$scope.newAddress = {};
	$scope.addresses=[];
	$scope.selectedAddress = undefined;
	
	$scope.searchAddress = function(searchedAddress){
		console.log('Request Search Address: '+searchedAddress);

		AddressService.searchAddress(searchedAddress)
		.then(
				function(data) {
					$scope.addresses = data;
				},
				function(errResponse){
					console.error('Error retrieving addresses');
				}
		);


	};
	
	$scope.collapseAndRemoveDetails = function(address) {
		
		console.log('Collapsing: '+address.summaryline);
	    $scope.showTheForm = false;
	    $scope.selectedAddress = undefined;
	    address.expanded = false;    
	    
	}
	
	
	$scope.expandAndfillDetails = function(address) {

		var selectedAddress = $scope.selectedAddress;
		
		if(selectedAddress != undefined ){
			
		    $scope.selectedAddress.expanded = false;			
		    $scope.selectedAddress = undefined;			
			
		}
		
		console.log('Expanding: '+address.summaryline);
		$scope.selectedAddress = address;
		$scope.showTheForm = true;
	
		address.expanded = true;
		

	}
	/*function searchAddress($scope){
		
		console.log('Request Search Address: ');
		
		AddressService.searchAddress()
			.then(
			function(d) {
				self.address = d;
			},
			function(errResponse){
				console.error('Error retrieving addresses');
			}
		);
	}*/

}]);