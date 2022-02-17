# IoweYou Application with MVVM Architecture and ROOM Database with LiveData and ViewModel

Backstory: Reason for the APP
A group of 4 friends - Anuradha, Raju, Baburao Apte and Ghanshyam used to hang out together quite often. Every time they went out for either a meal or some coffee, someone or the other always got away with not paying their share. It was becoming increasingly difficult to track expenses. Anuradha, being the smart and the responsible one amongst the four, decided to build a mobile application that will track all the expenses they make together as a group. 
She remembered that every time Raju got out of paying for his share, he used to say "Thanks buddy, I owe you" and hence decided to name the application - IOU(I Owe You) 

<p align="center">
  <a href="https://ibb.co/3RsmszX"><img src="https://i.ibb.co/Hh2T2dv/1.jpg" width="250" border="1"></a>
  <a href="https://ibb.co/2jHXDSH"><img src="https://i.ibb.co/9qSRFhS/2.jpg" width="250" border="1"></a>
  <a href="https://ibb.co/ZYbzQWL"><img src="https://i.ibb.co/SdZwkRs/3.jpg" width="250" border="1"></a>
  <a href="https://ibb.co/HDMxRYn"><img src="https://i.ibb.co/d03LX6G/4.jpg" width="250" border="1"></a>
  <a href="https://ibb.co/YbZXBf7"><img src="https://i.ibb.co/x3HYqzS/5.jpg" width="250" border="1"></a>
  <a href="https://ibb.co/Xxc7p35"><img src="https://i.ibb.co/s98KQ61/6.jpg" width="250" border="1"></a>
  <a href="https://ibb.co/847N9TJ"><img src="https://i.ibb.co/HpHBq10/t.jpg" width="250" border="1"></a>
  <a href="https://ibb.co/g7kR1NS"><img src="https://i.ibb.co/pPYLDcf/8.jpg" width="250" border="1"></a>
  <a href="https://ibb.co/1GXDB2G"><img src="https://i.ibb.co/k2DRzc2/9.jpg" width="250" border="1"></a>
  <a href="https://ibb.co/Tv54vdL"><img src="https://i.ibb.co/0D3CDvt/10.jpg" width="250" border="1"></a>
  <a href="https://ibb.co/syzPXRK"><img src="https://i.ibb.co/NZb3GC9/11.jpg" width="250" border="1"></a>
</p>

<br>
<br>

#### The app has following packages:
1. **adapters**: To render the recyclerview data on view
2. **models**: Here all the ENTITIES(Data) class are declared which stores the data information
3. **dao**: Data Access Objects - In this package DAO are defines which holds the methods for data operations
4. **database**: Room Database Class.
5. **repository**: These class used to access data from DB and Provide to ViewModels
6. **viewmodels**: These are intermeadioter between UI and Repositiry. It takes Data from Repo and provide to UI.
8. **ui**: All the User intrection classes placed here.
9. **utils**: this packege is used for declare application level utility class like Prefrense and Appconstants

#### The Users(Group) DB is pre initialize by createFromAsset method in Room database builder:


#### Defaults accounts are-:
1. **anuradha@gmail.com**:
2. **raju@gmail.com**:
3. **baburao@gmail.com**:
4. **ghanshyam@gmail.com**:

#### Defaults password for all account is-: **123**

#### The app flow:
1. **Splash**: After launching app 1st screen will be a splash screen with "IOU" name and has a 1000 milisecong delay.
It has condition (if user loggedin -> MainActivity else -> LoginActivity)
3. **Login**: From here User can Login by providing credientials.
5. **MainActivity**: Here i render Expense list and using 2 fragments for **profile** and **add expense**
6. **Profile Fragment**: It shows logged-in user information with **LogOut** button
7. **Add Expense Fragment**: From here user can add new expense in the list

#### Note:
1. I am using **ListAdapter with DiffUtil** in RecyclerViewAdapter (It improves performance by calculates the difference between two lists and outputs a list of update operations that converts the first list into the second one)
2. The MVVM Architecture workes great with Dagger2 **(Dependency Injection)**. Will work on that part in future updates.
3. I have Write some extra DB operations like INSERT, DELETE and UPDATE which are not in use now but it can be used when needed.
4. I thought **Side Navigation Drawer** is not required to show user profile so i use Simple Fragment for this with behaviour like Drawer :wink:

## Library reference resources:
1. Room: https://developer.android.com/topic/libraries/architecture/room.html
2. ViewModel: https://developer.android.com/jetpack/androidx/releases/lifecycle
3. Gson: https://github.com/google/gson
