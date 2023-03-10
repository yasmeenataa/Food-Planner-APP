# Chef Shift APP

It is a simple Food Planner App that help you : 

  1) find recipes by different methonds

  2) add yor favourite recipes to favourite list
  
  3) plann reciepes you want to cook for a week
  
  4) add plan in your mobile calender
 
  
# Team members : 

 1) Arwa Ashraf 

 2) Yasmeen Ataa
 
 # Technologies :
 
 1) Java. 
 2) MVP as architectural pattern.
 3) Room for local Storage. 
 4) Retrofit and GSON For Networking . 
 5) ViewBinding. 
 6) LiveData And MutableLiveData. 
 7) RxJava for Threading. 
 8) Firebase Authintication for login and SignUp. 
 9) Firebase RealTime for online Storage and retrival of Data. 
 10) Glide for display Images.
 11) Navigation Component.
 12) YouTubePlayer For play the Vedioes.
 
 # Full Scenario In App
 
   ### 1) Splash Screen with Simple Design include lottie animation and App Name and Slogan
 
 ![Splash](https://user-images.githubusercontent.com/55101407/218335585-d50b8b71-3a08-4a18-88d4-8202a61ae6ce.jpg)

   ### 2) Welcome Screen 
           1) you can Regist with google tap on Google button
           
           2) Sign UP with your Email tap on Sign Up button
           
           3) Log In with your Email tap on LogIn
           
           4) you can skip registration and explore the application tap on SKIP button
           
![Welcome](https://user-images.githubusercontent.com/55101407/218335846-08a04b57-3969-4d2d-a7a5-ec6ff683b951.jpg)

   ### 3) Sign Up Screen
   
              Username  , Emai ,Password rea required fields you have to enter the to register
              
              Password must have at least one numeric character ,one uppercase character,one lowercase character,
              
              one special symbol among @#$% and Password length    should be between 8 and 20.
   
![Sign_UP](https://user-images.githubusercontent.com/55101407/218336298-31a84329-965c-4e31-b89f-f10d71683ce7.jpg)
![SignUP_Auth](https://user-images.githubusercontent.com/55101407/218336343-c77ebc5c-2e88-462f-ba25-7d48e2d494a4.jpg)

   ### 4) Log In  Screen
   
   ![Sign In](https://user-images.githubusercontent.com/55101407/218336509-5662b55f-f598-4885-9984-0493836d6617.jpg)
   
   ### 5) Home Screen
            
            1) you have an inspiration meal for you if you click on it you can see full details about it 
            
            2) you can choose category you want to search by tap on any Category
            
            3)In Navigation button You can nav to Search ,Favourite ,Plan , Progile
            
  ![Home](https://user-images.githubusercontent.com/55101407/218336900-fa72ab99-d205-4c41-9221-c689e7109e33.jpg)
  
  
  ### 6) Search Screen
        
        you can search for reciep by different methods:
        
        1) search by meal name type name of meal in search bar then tap on target meal to nav to itd details
        
        2) search by Ingradiant tap on your ingradient to nav to all reciepes taht have this ingredient
        
        3) search by Country tap on wanted country to nav to all reciepes popular in this Area
        
        4) search by Category tap on your category to nav to all reciepes taht included in this category
  
  ![searchByName](https://user-images.githubusercontent.com/55101407/218341855-de377f43-fc89-48fa-9d8e-f54eb6036c78.jpg)     |         ![filter byIngredientd](https://user-images.githubusercontent.com/55101407/218342992-c13e1848-4acf-4885-b5a1-b7a15cc1c9f3.jpg)
  
  
  ### 7) Meal Details Screen 
  
          it shows all Details about the target meal image, instructions , ingredients and youtube video for how to cook this meal
          
          tap on icon favourite to add this meal to your favourite list
          
          tap on calender icon to plan this meal to week planner 
          
          tap on button add this meal to calender to add this meal to your mobile calender as event
  
 ![mealDetails](https://user-images.githubusercontent.com/55101407/218343142-771f3a22-b209-4ab8-ab4a-25bb7f31c778.jpg)
 
  ### 8) Favourite Meals Screen  
   
          it shows you your favourite meals 
 
 ![Favourite_List](https://user-images.githubusercontent.com/55101407/219335746-0f928e1f-f91d-4f45-bae6-2f4af42273b5.jpg)
 
   ### 9) Plan Screen 
   
 ![Day_of_week](https://user-images.githubusercontent.com/55101407/219336620-bb18d501-31fb-4c9c-808f-45217719f8b7.jpg)
  
  # Dependecy :
  - All dependencies are located in the Gradle Script file in the build.gradle folder. To implement them use implementation Libs.XXX.
 
           
