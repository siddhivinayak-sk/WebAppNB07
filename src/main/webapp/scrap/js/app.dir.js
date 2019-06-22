/* Directives */
app.directive('ngEnter', function() {
        return function(scope, element, attrs) {
            element.bind("keydown keypress", function(event) {
                if(event.which === 13) {
                    scope.$apply(function(){
                        scope.$eval(attrs.ngEnter, {'event': event});
                    });

                    event.preventDefault();
                }
            });
        };
});

app.directive('ngEdit', function() {
        return function(scope, element, attrs) {
            element.bind("keyup", function(event) {
                if(event.keyCode >64 && event.keyCode <91) {
                    scope.$apply(function(){
                        scope.$eval(attrs.ngEdit, {'event': event});
                    });
                    event.preventDefault();
                }
            });
        };
});

app.directive('validNumber', function() {
  return {
    require: '?ngModel',
    link: function(scope, element, attrs, ngModelCtrl) {
      if(!ngModelCtrl) {
        return; 
      }

      ngModelCtrl.$parsers.push(function(val) {
        if (angular.isUndefined(val)) {
            var val = '';
        }
        var clean = val.replace( /[^0-9]+/g, '');
        if (val !== clean) {
          ngModelCtrl.$setViewValue(clean);
          ngModelCtrl.$render();
        }
        return clean;
      });

      element.bind('keypress', function(event) {
        if(event.keyCode === 32) {
          event.preventDefault();
        }
      });
    }
  };
});



app.directive('ddesSelectCheckbox', function() {
    return {
        restrict: 'E',
        transclude: true,
        link: function(scope, element, attrs, ngModelCtrl) {
        },
        scope: {
            selectModel: '=selectModel',
            selectName: '=selectName',
            selectList: '=selectList',
            selectEvent: '=onSelect',
            isFirstAll: '=isFirstAll',
            check: '=check',
            show: '=show'
        },
        controller: function($scope) {
            $scope.showMultiselect = false;
            $scope.width = 50;
            $scope.firstItem = {};
            $scope.init = function() {
                if(undefined != $scope.selectList && null != $scope.selectList && $scope.selectList.length > 0) {
                    $scope.firstItem = $scope.selectList[0];
                }
            }
            $scope.change = function(item) {
                if($scope.isFirstAll) {
                    if(item[$scope.show] == $scope.firstItem[$scope.show]) {
                        if(item[$scope.check] == true) {
                            $scope.oldSelectList = angular.copy($scope.selectList);
                            $scope.oldSelectList[0][$scope.check] = false;
                            angular.forEach($scope.selectList, function(iSelect){
                                iSelect[$scope.check] = true;
                            });
                        }
                        else if(undefined != $scope.oldSelectList) {
                            $scope.selectList = angular.copy($scope.oldSelectList);
                        }
                    }
                }
                if(typeof selectEvent == 'function') {
                    selectEvent();
                }
            };
        },
        template: function(elem, attr) {return '<div class="multiselect" ng-init="init(); check = \'' + attr.check + '\'; show = \'' + attr.show + '\'"><div class="selectBox" ng-click="showMultiselect = !showMultiselect;"><select><option>{{selectName}}</option></select><div class="overSelect" onclick="angular.element(this).scope().width = this.offsetWidth;"></div></div><div class="checkboxes" style="width: {{width}}px;" ng-show="showMultiselect"><label ng-repeat="item in selectList track by $index"><input type="checkbox" ng-click="change(item);" ng-model="item.' + attr.check + '"/>{{item.' + attr.show + '}}</label></div></div>'
     }
    };
});

app.directive('ddesFormat', function() {
    return {
        require: '?ngModel',
        restrict: 'A',
        transclude: true,
        link: function(scope, element, attrs, ngModelCtrl) {
            if(!ngModelCtrl || !attrs.ddesFormat) {
              return; 
            }
            
            var getSignedInteger = function(data) {
                var clean = '';
                if(data.indexOf('-') >= 0) {
                    clean = data.replace(/[^0-9-]+/g, '');
                }
                else {
                    clean = data.replace(/[^0-9]+/g, '');
                }
                return clean;
            };

            var getSignedNumber = function(data) {
                var clean = '';
                if(data.indexOf('-') >= 0) {
                    clean = data.replace(/-[^0-9\.]+/g, '');
                }
                else {
                    clean = data.replace(/[^0-9\.]+/g, '');
                }
                return clean;
            };
            
            var bind32 = function(element) {
                element.bind('keypress', function(event) {
                    if(event.keyCode === 32) {
                      event.preventDefault();
                    }
                });
            };
            
            var bind45 = function(element) {
                element.bind('keypress', function(event) {
                    if(event.keyCode === 45) {
                      event.preventDefault();
                    }
                });
            };
            
            var getChoppedNumber = function(clean, length) {
                length = length + 1;
                if(clean.indexOf('.') >= 0 && undefined != (clean.charAt(clean.indexOf('.') + length)) && null != (clean.charAt(clean.indexOf('.') + length)) && '' != (clean.charAt(clean.indexOf('.') + length))) {
                    clean = clean.substring(0, clean.indexOf('.') + length);
                }
                return clean;
            };
            
            if(1 == attrs.ddesFormat) {
                ngModelCtrl.$parsers.push(function(val) {
                    if (angular.isUndefined(val)) {
                        var val = '';
                    }
                    var clean = getSignedInteger(val);
                    if (val !== clean) {
                      ngModelCtrl.$setViewValue(clean);
                      ngModelCtrl.$render();
                    }
                    return clean;
                });
                bind32(element);
                bind45(element);
            }
            else if(2 == attrs.ddesFormat) {
                ngModelCtrl.$parsers.push(function(val) {
                    if (angular.isUndefined(val)) {
                        var val = '';
                    }
                    var clean = getSignedNumber(val);
                    clean = getChoppedNumber(clean, 1);
                    if (val !== clean) {
                      ngModelCtrl.$setViewValue(clean);
                      ngModelCtrl.$render();
                    }
                    return clean;
                });
                bind32(element);
                bind45(element);
            }
            else if(3 == attrs.ddesFormat) {
                ngModelCtrl.$parsers.push(function(val) {
                    if (angular.isUndefined(val)) {
                        var val = '';
                    }
                    var clean = getSignedNumber(val);
                    clean = getChoppedNumber(clean, 3);
                    if (val !== clean) {
                      ngModelCtrl.$setViewValue(clean);
                      ngModelCtrl.$render();
                    }
                    return clean;
                });
                bind32(element);
                bind45(element);
            }
            else if(4 == attrs.ddesFormat) {
                ngModelCtrl.$parsers.push(function(val) {
                    if (angular.isUndefined(val)) {
                        var val = '';
                    }
                    var clean = getSignedNumber(val);
                    clean = getChoppedNumber(clean, 1);
                    if (val !== clean) {
                      ngModelCtrl.$setViewValue(clean);
                      ngModelCtrl.$render();
                    }
                    return clean;
                });
                bind32(element);
                bind45(element);
            }
            else if(5 == attrs.ddesFormat) {
            }
            else if(6 == attrs.ddesFormat) {
                ngModelCtrl.$parsers.push(function(val) {
                    if (angular.isUndefined(val)) {
                        var val = '';
                    }
                    var clean = getSignedNumber(val);
                    clean = getChoppedNumber(clean, 2);
                    if (val !== clean) {
                      ngModelCtrl.$setViewValue(clean);
                      ngModelCtrl.$render();
                    }
                    return clean;
                });
                bind32(element);
                bind45(element);
            }
            else if(7 == attrs.ddesFormat) {
                ngModelCtrl.$parsers.push(function(val) {
                    if (angular.isUndefined(val)) {
                        var val = '';
                    }
                    var clean = getSignedNumber(val);
                    clean = getChoppedNumber(clean, 2);
                    if (val !== clean) {
                      ngModelCtrl.$setViewValue(clean);
                      ngModelCtrl.$render();
                    }
                    return clean;
                });
                bind32(element);
            }
        }    
    };
});

app.directive('ddesColor', function() {
    return {
        restrict: 'A',
        transclude: true,
        link: function(scope, element, attrs, ngModelCtrl) {
            if(undefined == element || null == element || element.length <= 0) {
                return;
            }
            angular.forEach(element, function(ele){
                if(0 == attrs.ddesColor) {
                    ele.style.backgroundColor = '#FFF380';
                }
                else if(1 == attrs.ddesColor) {
                    ele.style.backgroundColor = '#CFECEC';
                }
                else if(2 == attrs.ddesColor) {
                    ele.style.backgroundColor = '#F7BE81';
                }
                else {
                    ele.style.backgroundColor = '#FFF380';
                }
            });
        }    
    };
});

app.directive('ddesRequirement', function() {
    return {
        restrict: 'E',
        transclude: true,
        link: function(scope, element, attrs, ngModelCtrl) {
        },
        scope: {
        },
        controller: function($scope) {
        },
        template: function(elem, attr) {
            return ''
        }
    };
});

