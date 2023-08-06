# Aisle Technical Task

This repository contains the implementation of the Aisle Technical Task, which is an Android application developed using Kotlin and Android Studio. The app makes use of various libraries and APIs to provide a seamless user experience.

## Project Description

The Aisle Technical Task Android application allows users to perform phone number authentication using the provided APIs. It includes features to log in with a phone number, verify OTP, and retrieve a list of test profiles.

## API Endpoints

The app communicates with the server using the following API endpoints:

1. **Phone Number API**
   - Method: POST
   - Endpoint: `/users/phone_number_login`
   - Parameter: `{'number' = "+919876543212"}`

2. **OTP API**
   - Method: POST
   - Endpoint: `/users/verify_otp`
   - Parameter: `{'number' = "+919876543212", 'otp' => "1234"}`

3. **Notes API**
   - Method: GET
   - Endpoint: `/users/test_profile_list`
   - Header: `{'Authorization' = ‘auth_toke`

## Dependencies

The application utilizes the following libraries and technologies:

- Kotlin: The primary programming language used for Android development.
- Dagger: Dependency injection framework for managing dependencies.
- Retrofit: A type-safe HTTP client for making API calls.
- Datastore Preference: A modern data storage solution for managing app preferences.

## Installation

To run the application on your local machine, follow these steps:

1. Clone the repository to your local machine:

```bash
git clone https://github.com/kartikeysaran/AisleTechnicalTask.git
```
2. Open the project in Android Studio.

3. Build and run the application on an Android emulator or a physical device.


## Usage

The application flow is as follows:

1. **Check Auth Token**: Upon launching the app, the first step is to check whether the `authToken` is present in the Datastore preference. If the `authToken` is found, the user is directly forwarded to the Dashboard, bypassing the phone number authentication.

2. **Phone Number Authentication**: If the `authToken` is not present (i.e., the user is not logged in), the app prompts the user to log in with their phone number. The user needs to enter their phone number, and then the app will make a POST request to the server with the provided phone number to initiate the authentication process.

3. **Verify OTP**: After entering the phone number, the user will receive an OTP on their phone. The user needs to enter the received OTP in the app, and then the app will make a POST request to the server with the phone number and OTP to verify the user's identity.

4. **Save Auth Token**: Once the OTP is successfully verified, the server will respond with an `authToken`. This `authToken` is then saved to the Datastore preference, allowing the user to bypass the phone number authentication in future app launches.

5. **Forward to Dashboard**: After successfully obtaining the `authToken`, the user is forwarded to the Dashboard, where they can access the app's features and view the list of test profiles.

Please note that the above flow provides an overview of the user interactions with the application, and the actual implementation details may vary based on your specific project requirements.

## UI Design

The app's user interface has been designed based on the provided Figma file. The UI design can be accessed at the following link: [Figma Design](https://www.figma.com/file/Mh6Jb8gBxT9MewfWkKNwOV/Tech-Challenge?type=design&node-id=0-1&mode=design&t=gn9bQuii6lOhSNlX-0)

## Assignment Details

For detailed information about the assignment and its requirements, please refer to the provided document: [Assignment Details](https://doc.clickup.com/7237942/p/h/6ww9p-36904/ff3526232d05fb1)

---
Feel free to further customize the above README.md template to include any specific information about your project. If you have any additional instructions or details, make sure to include them in the README as well.
