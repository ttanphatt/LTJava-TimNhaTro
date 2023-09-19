// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
import firebase from "firebase/compat/app";
import { getFirestore } from "firebase/firestore";

// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyC0__OqvffE1mW2NuHqz351_LQGETFtyyo",
  authDomain: "phongtro-397206.firebaseapp.com",
  databaseURL: "https://phongtro-397206-default-rtdb.firebaseio.com",
  projectId: "phongtro-397206",
  storageBucket: "phongtro-397206.appspot.com",
  messagingSenderId: "1042189002331",
  appId: "1:1042189002331:web:291a5559985c24a59ac17e",
  measurementId: "G-6C1E1MTYGM"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);
const db = getFirestore(app);

export {db}