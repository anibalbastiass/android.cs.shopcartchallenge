# Cornershop Cart app with dynamic counters
> Challenge app for Android Engineer technical test, very useful and funny app for apply Android Architecture components.

This project is a Cornershop technical test for apply Android Enginner job. 
Goal: Build a app consuming a ElasticBeanstalk AWS NodeJS instance with a API REST for CRUD counter cart and keep persistence between API REST and local database. Observable vars and ViewModel/LiveData refresh data when is changing counters.

## Steps

- Backend (Node JS):
  - From CS team, mount Express Node JS server into AWS Instance with Elastic Beanstalk instance.
  - This project contains by default port 3000, this changes to 8081 for can send and receive data.
  - These steps had success and was mounted in http://csapirest-env.928apjmpyq.us-east-1.elasticbeanstalk.com
  - For this test, this instance is a simple API REST without high security settings.

- Frontend (Android):
  - From a personal project with a solid, scalable and clean architecture for Android apps (CLEAN + MVVM), begins complete features for this project.
  - Work with TDD (Test Drive Development) for build with Unit tests with Mockito (Unit tests).

This project contains the following milestones

- Mount a EC2 AWS Instance for that Android app can consume a remote API REST (think as Released Android app). 
- Build a scalable and stable architecture for Android App: MVVM + CLEAN Architecture
- Apply Android Architecture Components: Navigation, View Model, Live Data, Room, Databinding
- Use Android X dependencies
- Keep clean code and use minimal dependencies

- Navigation:
This app contains a single activity with a Navigation Controller with a Fragment: `ShopCartFragment`

- ViewModel:
This component is useful for share and save data throw Live Data

- Databinding and Observables:
These are the ones in charge of refreshing data for UI throw Observable vars.

- Realm (Room in a future):
Save and get local database for keep persistence if the device lost internet connection and when this go back, send pendent data and sync these.

- Unit Testing
Implement test for ViewModel throw captors, mocks for check stats for LiveData too.

- Continuous Integration
With Github Actions feature (Top tab), this repository is processing throw CI.

## Screenshot

![Shop Cart App](https://raw.githubusercontent.com/anibalbastiass/android.cs.shopcartchallenge/develop/screenshots/photo1.png)


## Resources

- Postman API REST Collection: https://www.getpostman.com/collections/eeeb4e665d9dd679177d
- API REST Base Endpoint: http://csapirest-env.928apjmpyq.us-east-1.elasticbeanstalk.com

