angular.module('starter.controllers', [])
.controller('AppCtrl', function($scope, $ionicModal, $timeout) {
  // Form data for the login modal
  $scope.loginData = {};
  // Create the login modal that we will use later
  $ionicModal.fromTemplateUrl('templates/login.html', {
    scope: $scope
  }).then(function(modal) {
    $scope.modal = modal;
  });
  //Triggered in the login modal to close it
  $scope.closeLogin = function() {
    $scope.modal.hide();
  };
  // Open the login modal
  $scope.login = function() {
    $scope.modal.show();
  };
  // Perform the login action when the user submits the login form
  $scope.doLogin = function() {
    console.log('Doing login', $scope.loginData);
    // Simulate a login delay. Remove this and replace with your login
    // code if using a login system
    $timeout(function() {
      $scope.closeLogin();
    }, 1000);
  };
})
.controller('Registration', function($scope, $ionicModal, $timeout){
   //registration  modal
  $scope.registerData = {};
  // Create the register modal that we will use later
  $ionicModal.fromTemplateUrl('templates/playlists.html', {
    scope: $scope
  }).then(function(modal) {
    $scope.modal = modal;
  });
  // Triggered in the register modal to close it
  $scope.closeRegister = function() {
    $scope.modal.hide();
  };
  // Open the register modal 
  $scope.register = function() {
    $scope.modal.show();
  }; 
  // Perform the register action when the user submits the login form  
  $scope.doRegister = function() {
    console.log('Doing Registeration', $scope.registerData);
    // Simulate a register delay
    $timeout(function() {
      $scope.closeRegister();
    },1000);
  };
})
.controller('PlaylistCtrl', function($scope, $stateParams) {
})

.controller('mycontroller1', function ($scope, $http){
            $scope.converttoSpeech = function(){
                 var callback =$http.get("https://newsapi.org/v1/articles?source=cnn&apiKey=7a51c92205a54bd4ab71c00a9e780b73");
                      callback.success(function(data)
                                      {
                          $scope.NewsTitle = data.articles[0].title;
                        $scope.News = data.articles[0].description;
                          $scope.img = data.articles[0].urlToImage;
                      })
                        
                    
            }
            $scope.checksentiment = function(){
              var text = $scope.NewsTitle;
                var sent=$http.get("https://gateway-a.watsonplatform.net/calls/text/TextGetTextSentiment?apikey=49d42a08b29490c409f92d6389f3292afce91968&outputMode=json&text="+text);
                sent.success(function (data){
                    $scope.sentiment="\n"+"Sentiment Type:"+data.docSentiment.type+"Sentiment Score:"+data.docSentiment.score;
                })
            }
});
