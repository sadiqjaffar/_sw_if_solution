database=firebase.database();

            var ref = database.ref('EmployeesData/Health Device Name/LIFE');
            var refer = database.ref('EmployeesData/Employee Details');

            ref.on('value',gotData,errData);
            refer.on('value',gotData2);
            function logout(){
              document.getElementById("user_div").style.display = "none";
              document.getElementById("login_div").style.display = "block";
              firebase.auth().signOut();
            }

            function gotData2(data)
            {
              console.log(data.val());
              var life=data.val();
              var key=Object.keys(life);
              console.log(key);
               var k=key;
               var name=life[k].name;
               var email=life[k].email;
               console.log(name);
               document.getElementById("Name").innerHTML=name;
               document.getElementById("Email").innerHTML=email;

            }

            function gotData(data){
              console.log(data.val());
              var LIFE=data.val();


              var keys=Object.keys(LIFE);
              console.log(keys);
              document.getElementById("ll").innerHTML=keys;
               var j =keys;

               var blood_pressure=LIFE[j].blood_pressure;
               console.log(blood_pressure);
               var oxygen_saturation=LIFE[j].oxygen_saturation;
               var heart_rate=LIFE[j].heart_rate;
               var temperature=LIFE[j].temperature;
               var health_stat="Normal";



               document.getElementById("Bp").innerHTML=blood_pressure;
               document.getElementById("Oxy").innerHTML=oxygen_saturation;
               document.getElementById("h").innerHTML=heart_rate;
               document.getElementById("te").innerHTML=temperature;
               document.getElementById("ht").innerHTML=health_stat;


            }
            function errData(err){
              console.log('Error');
              console.log(err);
            }
            function next(){
               window.location.href="records.html"
            }


