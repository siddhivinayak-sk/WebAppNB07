app.controller('appController', function($scope, $http, $window, $timeout, $uibModal, $log) {
    $scope.fn = app.fn;
    $scope.items1 = [{id: 1, value: 'A1', b: true}, {id: 2, value: 'A2',  b: false}, {id: 3, value: 'A3',  b: false}, {id: 4, value: 'A4',  b: false}, {id: 5, value: 'A5',  b: false}];
    $scope.items2 = [{id: 1, value: 'A1', b: false}, {id: 2, value: 'A2',  b: true}, {id: 3, value: 'A3',  b: false}, {id: 4, value: 'A4',  b: false}, {id: 5, value: 'A5',  b: false}];
    $scope.items3 = [{id: 1, value: 'A1', b: false}, {id: 2, value: 'A2',  b: false}, {id: 3, value: 'A3',  b: true}, {id: 4, value: 'A4',  b: false}, {id: 5, value: 'A5',  b: false}];
    $scope.items4 = [{id: 1, value: 'A1', b: false}, {id: 2, value: 'A2',  b: false}, {id: 3, value: 'A3',  b: false}, {id: 4, value: 'A4',  b: false}, {id: 5, value: 'A5',  b: true}];
    
    
    $scope.fn.change = function(var1) {
    }; 
    
});