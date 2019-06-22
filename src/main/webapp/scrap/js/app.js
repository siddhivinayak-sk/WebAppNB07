var app = angular.module('app', ['ui.bootstrap', 'ngSanitize', 'ui.select']);
app.fn = {};
app.model = {};
app.model.config = {};


/* Methods */
app.fn.formPost = function(optionList, paramsList) {
    var options = angular.extend({
        url: '',
        method: 'post',
        myWindow: null,
        warType: 1
    }, optionList);
    var params = angular.extend({
        createdBy: 'formPost'
    }, paramsList);
    var doc = null;
    var form = null;
    var serverPath = null;
    if(undefined != options.serverPath && null != options.serverPath && '' != options.serverPath) {
        serverPath = options.serverPath;
    }
    else {
        serverPath = app.model.config.serverPath;
    }
    if(undefined != options.url && null != options.url && options.url.indexOf('/') != 0) {
        options.url = '/' + options.url;
    }
    //Post in specified window
    if(null != options.myWindow) {
        doc = options.myWindow.document;
        form = doc.createElement('form');
        form.action = serverPath + app.model.config.baseContext + options.url;
        form.method = options.method;
    }
    //Post in parent window
    else if(1 == options.warType) {
        doc = window.parent.document;
        form = doc.createElement('form');
        form.action = app.model.config.baseContext + options.url;
        form.method = options.method;
    }
    //Post in current window
    else {
        doc = window.document;
        form = doc.createElement('form');
        form.action = app.model.config.applicationContext + options.url;
        form.method = options.method;
    }
    for(param in params) {
        var name = param;
        var value = params[name];
        if(undefined != value && null != value) {
            var element = doc.createElement('input');
            element.type = 'hidden';
            element.name = name;
            element.value = value;
            form.appendChild(element);
        }
    }
    if(undefined != form && null != form) {
        doc.body.appendChild(form);
        form.submit();
    }
}

app.fn.request = function(options, $http) {
    var parameters = angular.extend({
        method: 'POST',
        url: '',
        params: undefined,
        //data: {},
        //headers: {contentType: 'json'},
        responseType: 'json',
        logoutUrl: '/controller/logout',
        containerUrl: '/controller/ddes',
        fromPostType: 1,
        warType: 1,
        isRunException: true,
        beginAction: function() {},
        endAction: function(response) {},
        successAction: function(response) {},
        cleanupBeforeLogin: function(response){}
    }, options);
    
    parameters.beginAction();
    
    $http(parameters).then(function(response){
        //status 200-299
       responseData = response.data;
       if(null != responseData && null != responseData) {
           parameters.endAction(response);

           var requestedUrl = responseData.requestedUrl;
           var datasheetNo = responseData.datasheetNo;
           var masterSampleNo = responseData.masterSampleNo;
           var testPropertyId = responseData.testPropertyId;
           var testlineId = responseData.testlineId;
           var testlineVer = responseData.testlineVer;
           
           if(undefined != requestedUrl && null != requestedUrl && '' != requestedUrl) {
               if(1 == parameters.fromPostType) {
                   app.fn.formPost({
                       url: requestedUrl,
                       warType: parameters.warType
                   }, {
                       "datasheetNo": datasheetNo,
                       "testPropertyID": testPropertyId,
                       "masterSampleNo": masterSampleNo,
                       "datasheetView": undefined,
                       "tabIndex": undefined,
                       "reqChange": undefined,
                       "testlineId": testlineId,
                       "testlineVer": testlineVer,
                       "serverPath": app.model.config.applicationContext + '/'
                   });
               }
               else {
                window.parent.location = app.model.config.baseContext + parameters.containerUrl + '?' 
                        + 'requestedUrl=' + requestedUrl 
                        + 'datasheetNo=' + datasheetNo 
                        + 'masterSampleNo=' + masterSampleNo 
                        + 'testPropertyId=' + testPropertyId 
                        + 'testlineId=' + testlineId 
                        + 'testlineVer=' + testlineVer;
               }
           }
           else {
               parameters.successAction(response);
           }
       }
    }, function(response){
        //status all except range 200-299
        if(parameters.isRunException) {
            parameters.endAction(response);
            parameters.cleanupBeforeLogin(response);
            $http({url: app.model.config.applicationContext + parameters.logoutUrl}).then(function(r1){}, function(r2){});
            window.parent.location = app.model.config.baseContext + parameters.logoutUrl;
        }
        else {
            parameters.successAction(response);
        }
    });
};


app.fn.openModelTemplate = function(param, $uibModal) {
    var options = angular.extend({
        animation: true,
        ariaLabelledBy: 'modal-title',
        ariaDescribedBy: 'modal-body',
        templateUrl: '',
        controller: '',
        controllerAs: '$ctrl',
        size: 'lg',
        component: '',
        resolve: {
            modelData: function() {return 'No Data'}
        },
        approveOk: function(data) {console.log('Ok:' + data);},
        rejectCancel: function(data) {console.log('Cancel:' + data);}
        }, param);
    var modelInstance = $uibModal.open(options);
    modelInstance.result.then(options.approveOk, options.rejectCancel);
};

app.fn.openModel = function($uibModal, modelName, inModelData, onApprove, onCancel, size) {
    var options = {
        templateUrl: modelName + '.html',
        component: modelName + 'Component',
        resolve: {
            modelData: function() {return inModelData;}
        },
        approveOk: function(data) {
            //console.log(modelName + ' Ok:' + data);
            if(typeof onApprove === "function") {
                onApprove(data);
            }
        },
        rejectCancel: function(data) {
            //console.log(modelName + ' Cancel:' + data);
            if(typeof onCancel === "function") {
                onCancel(data);
            }
        }
    };
    if(undefined != size) {
        options.size = size;
    }
    app.fn.openModelTemplate(options, $uibModal);
};

app.fn.getReportPathAndGenerateReport = function($http, reportParmas) {
    var options = {
        method: 'GET',
        url: reportParmas.urlToGetReportContext,
        params: {
            "masterSample": reportParmas.masterSampleNo,
            "isARGReport": reportParmas.isARGReport
        },
        beginAction: reportParmas.processingStart,
        endAction: reportParmas.processingEnd,
        successAction: function(response) {
            if(typeof reportParmas.generateAction === "function") {
                var reportContext = response.data.message;
                var reportServerPath = '';
                //reportServerPath = 'http://10.2.114.100';
                var bakedReportOptions = angular.extend({
                    method: 'GET',
                    url: reportServerPath + reportContext + reportParmas.reportUrl,
                    params: reportParmas.params,
                    responseType: 'text',
                    isRunException: false,
                    beginAction: reportParmas.processingStart,
                    endAction: reportParmas.processingEnd,
//                    headers: {
//                       'Content-type': 'application/xml',
//                       'Accept':'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8',
//                       'Access-control-allow-origin': '*',
//                       'Access-Control-Allow-Methods': 'GET, POST, PATCH, PUT, DELETE, OPTIONS',
//                       'Access-Control-Allow-Headers': 'Origin, Content-Type, X-Auth-Token'
//                    },
                    successAction: function(response) {
                        var xml = response.data;
                        var model = {};
                        if(undefined != xml || null != xml) {
                            var responseXml = $.parseXML(xml);
                            $(responseXml).find('Report_Generation').each(function(){
                                model.ReportNo = (undefined != $(this).find('ReportNo'))?$(this).find('ReportNo').text():'';
                                model.Result = (undefined != $(this).find('Result'))?$(this).find('Result').text():'';
                                model.output_file_path = (undefined != $(this).find('output_file_path'))?$(this).find('output_file_path').text():'';
                                model.output_client_file_path = (undefined != $(this).find('output_client_file_path'))?$(this).find('output_client_file_path').text():'';
                                model.excel_output_client_file_path = (undefined != $(this).find('excel_output_client_file_path'))?$(this).find('excel_output_client_file_path').text():'';
                                model.Error_Id = (undefined != $(this).find('Error_Id'))?$(this).find('Error_Id').text():'';
                                model.Error_Type = (undefined != $(this).find('Error_Type'))?$(this).find('Error_Type').text():'';
                                model.Error_Description = (undefined != $(this).find('Error_Description'))?$(this).find('Error_Description').text():'';
                                model.Error_Description_Detail = (undefined != $(this).find('Error_Description_Detail'))?$(this).find('Error_Description_Detail').text():'';
                                model.log = (undefined != $(this).find('log'))?$(this).find('log').text():'';
                                model.unmapped_test = (undefined != $(this).find('unmapped_test'))?$(this).find('unmapped_test').text():'';
                                
                                model.poutput_file_path = model.output_file_path.replaceAll('/', '/ ').replace(/\\/g, '\\\u200B');
                                model.poutput_client_file_path = model.output_client_file_path.replace(/\\/g, '\\\u200B');
                                model.pexcel_output_client_file_path = model.excel_output_client_file_path.replaceAll('/', '/ ').replace(/\\/g, '\\\u200B');
                                
                                if(undefined != model.output_file_path && '' != model.poutput_file_path) {
                                    model.p1 = true;
                                }
                                else if(undefined != model.output_client_file_path && '' != model.output_client_file_path) {
                                    model.p2 = true;
                                }
                                else if(undefined != model.excel_output_client_file_path && '' != model.excel_output_client_file_path) {
                                    model.p3 = true;
                                }
                            });
                        }
                        else {
                            model.Error_Description = 'Error: Server is not responding';
                        }
                        if(undefined == model.ReportNo || '' == model.ReportNo) {
                            model.Error_Description = 'Error 404: Server is not responding';
                        }
                        reportParmas.generateAction(model);
                    }
                }, reportParmas);
                app.fn.request(bakedReportOptions, $http);
            }
        }
    };
    app.fn.request(options, $http);
};

app.fn.downloadFile = function(path, url, options) {
    var newWindow = window.open(app.model.config.baseContext, '_blank');
    if(path.indexOf('\\') != -1){
        path = path.substr(1);
        path = path.replace(/\\/g,'/');
        path = '/opt/share'+ path;
    }
    var fileName = path;
    if(path.lastIndexOf('\\') != -1){
        fileName = path.substring(path.lastIndexOf('\\')+1);
    }else if(path.lastIndexOf('/') != -1){
        fileName = path.substring(path.lastIndexOf('/')+1);
    }
    //path = encodeURIComponent(path);
    if(undefined != options && null != options) {
        options.serverPath = app.model.config.applicationContext + '/';
    }
    else {
        options = {
            "filename": path,
            "serverPath": app.model.config.applicationContext + '/'
        };
    }
    app.fn.formPost({
        "url": url + '/' + fileName,
        "myWindow": newWindow,
        "method": 'get'
    }, options);
};

app.fn.clearSessionStorage = function() {
    if(window.parent) {
        window.parent.sessionStorage.clear();
    }
    window.sessionStorage.clear();
};

app.fn.push = function(sourceList, element) {
    if(undefined != element && null != element) {
        var isAvailable = false;
        angular.forEach(sourceList, function(ele) {
            if(element == ele) {
                isAvailable = true;
            }
        });
        if(!isAvailable) {
            if(undefined == sourceList || null == sourceList || sourceList.length < 0) {
                sourceList = [];
            }
            sourceList.push(element);
        }
    }
    return sourceList;
};
app.fn.getCoords = function(elem) {
    var box = elem.getBoundingClientRect();
    var body = document.body;
    var docEl = document.documentElement;
    var scrollTop = window.pageYOffset || docEl.scrollTop || body.scrollTop;
    var scrollLeft = window.pageXOffset || docEl.scrollLeft || body.scrollLeft;
    var clientTop = docEl.clientTop || body.clientTop || 0;
    var clientLeft = docEl.clientLeft || body.clientLeft || 0;
    var top  = box.top +  scrollTop - clientTop;
    var left = box.left + scrollLeft - clientLeft;
    return { top: Math.round(top), left: Math.round(left) };
};

//String Prototype Methods
String.prototype.replaceAll = function(search, replace) {
    if (replace === undefined) {
        return this.toString();
    }
    return this.replace(new RegExp('[' + search + ']', 'g'), replace);
};