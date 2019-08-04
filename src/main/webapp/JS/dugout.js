/**
 * 
 */

var movieapp = angular.module('movieapp', ['ngRoute']); 

movieapp.config(function($routeProvider) {
	$routeProvider
	.when('/resume', {
		templateUrl : 'resume.html'
	})
	.when('/teams', {
		templateUrl : 'teams.html'
	})
	.when('/addplayer', {
		templateUrl : 'addplayer.html',
		controller : 'addPlayerController'
	})
	.when('/summary', {
		templateUrl : 'summary.html'
	})
	.when('/stack', {
		templateUrl : 'stack.html'
	})
	.when('/fancysearch', {
		templateUrl : 'fancy.html', 
		controller : 'movieFancyController'
	})
	.otherwise({
		redirectTo: '/search'
	});
	
});


 movieapp.controller('moviecontroller', function($scope, $http) {
	$scope.appName = 'My Movie App, by Chris the jet Cullum';
	$scope.showsearch = 'true';
	
	$scope.navName = 'Dugout';
	$scope.showEmail = true;
	$scope.showSearch = true;
	$scope.showEditDelete = false;
	
	$scope.genres = ['Action', 'SyFy', 'Comedy', 'Horror'];
	
	$scope.updateMovie = function(movieToUpdate) {
		console.log('selected player to update: ' + angular.toJson(movieToUpdate));
		 $scope.movieToUpdate = angular.copy(movieToUpdate);
		$scope.showEditDelete = true;
		$scope.showSearch = false;
		$scope.isUpdateButtonDisabled = false;
		$scope.isDeleteButtonDisabled = false;
		$scope.updateStatus = '';

		
	}
	
	$scope.getMovies = function() {
        console.log('get all the teams');
        $scope.movies = [{"title":"retrieving teams.."}];
        
        $scope.showSearch = true;
        $scope.showEditDelete = false;
        
        $http.get("/movieapi/webapi/movies")
        .then(function(response) {
            $scope.movies= response.data;
            console.log('number of movies: ' + $scope.movies.length);
        }, function(response) {
            console.log('error HTTP GET movies: ' + response.status);
        });
    
	};
	
	$scope.returnToSearch = function() {
		$scope.showEditDelete = false;
		$scope.showSearch = true;
		$scope.getMovies();
	
	}
	
	$scope.deleteMovie = function(key) {
		console.log('Delete Movie: ' + key);
		$http.delete("/movieapi/webapi/movies/" + key)
		.then(function(response) {
			$scope.isUpdateButtonDisabled = true;
			$scope.isDeleteButtonDisabled = true;
			$scope.updateStatus = 'Delete Successful';	
			console.log('number of movies deleted: ' + response.data.length);
		}, function(response) {
			console.log('error HTTP DELETE movies: ' + response.status);
			$scope.updateStatus = 'Delete Error, ' + response.data.message;
		});
	}
	
	$scope.putMovie = function(movieToUpdate) {
		$scope.payload =  angular.toJson(movieToUpdate, false);
		
		console.log('json movie to update: ' + $scope.payload);
		
		$http.put("/movieapi/webapi/movies", $scope.payload)
		.then(function success(response) {
			$scope.isUpdateButtonDisabled = true;
			console.log('put status: ' + response.status);
			$scope.updateStatus = 'Update Successful';
				},
				function error(response) {
					console.log('put error: ' + response.status);
					$scope.updateStatus = 'Update Error, ' + response.data.message;
				}
		);
	
	};
	
	$scope.getMovies();
	
 });
 
 movieapp.controller('movieCreateController', function($scope, $http ) {	
		
		$scope.postMovie = function() {	
			$scope.jsonObject = angular.toJson($scope.newMovie, false);
			console.log('new movie: ' + $scope.jsonObject);		
			
			$http.post("/movieapi/webapi/movies", $scope.jsonObject)
			.then(
					function success(response) {					
						console.log('status: ' + response.status);
						$scope.createStatus = 'successful insert of new movie';
						$scope.successfulInsert = true;
					},
					function error(response) {
						console.log('error, return status: ' + response.status);
						$scope.createStatus = 'insert error, ' + response.data.message;
					}
			);	
			
		};
		
		$scope.clearMovie = function() {
			$scope.createStatus = 'Enter new movie information';
			$scope.successfulInsert = false;		
			$scope.newMovie = {
					title : '',
					description : '',
					genre : '',
					releaseYear : ''
					
			};
		}
		
	});
 
 	movieapp.controller('movieFancyController', function($scope, $http ) {
 		
 		$scope.getFancySearch = function() {
 			console.log('fancy movie search');
 			console.log('fancysearch: ' + angular.toJson($scope.fancysearch, false));
 			
 			var config = { params: $scope.fancysearch }
 			
 			$http.get("/movieapi/webapi/movies/fancysearch", config)
 			.then(
 					function(response) {
 						$scope.searchResults = response.data;
 					},
 					function error(response) {
 						console.log('error, return status : ' +  response.status);
 					}
 					
 				);
 			
 		},
 		
 		$scope.clearFancySearch = function() {
 	         $scope.clearFancySearch = function() {
 	         		$scope.fancysearch.english = false;
 	         		$scope.fancysearch.french = false;
 	         		$scope.fancysearch.german = false;
 	         		$scope.fancysearch.spanish = false;
 	         		
 	         		$scope.fancysearch.media = '';
 	         		
 	         		$scope.fancysearch.startdate = '';
 	         		$scope.fancysearch.enddate = '';
 	         		
 	         		$scope.searchResults = '';
 	         	}
;
 		}
 		
 	});