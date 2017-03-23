/**
 * Created by Girish on 3/17/2017.
 */
angular.module("apper",[])
.controller('control',function ($scope,$http) {
    $scope.search=function () {

        $http.get("http://127.0.0.1:8888/knowledge",{params: {inp:$scope.input1}}).success(function (data) {

           if (data != null) {
               $scope.out= {
                 //  "score": data.results[0].calories,
                   "image": data.results[0].link,
                  // "qt":data.results[0].quantity,
                   "text1":data.results[0].text1
               }
           }
        });
           document.getElementById("col").style.display='block';


    }
})