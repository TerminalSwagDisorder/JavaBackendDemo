import {Routes, Route, BrowserRouter, Link } from "react-router-dom";
import React, { useState, useEffect } from "react";
import "./style/style.scss";
import Home from "./components/Home";
import Profile from "./components/Profile";
import NavBar from "./components/Nav";
import Signin from "./components/Signin";
import Signup from "./components/Signup";
import Admin from "./components/Admin";
import DashboardAdmin from "./components/DashboardAdmin";
import UsersAdmin from "./components/UsersAdmin";
import PartsDisplay from "./components/PartsDisplay";
import ComputerWizard from './components/ComputerWizard';
import ComputerWizardBrowse from './components/ComputerWizardBrowse';
import ShoppingCart from './components/ShoppingCart';
import { ThemeContext, ThemeProvider, fetchUsers, fetchDynamicData, fetchSearchIdData, fetchDataAmount, handleSignin, handleSignup, handleSignout, checkIfSignedIn, refreshProfile, handleCredentialChange, updateDynamicData, deleteDynamicData } from "./api/api";
import { useSelector, useDispatch } from "react-redux";



function App() {
	const [currentUser, setCurrentUser] = useState(null);

	const shoppingCart = useSelector((state) => state.shoppingCart.shoppingCart);
	const dispatch = useDispatch();
	const cartItems = Object.values(shoppingCart);
  	const totalCartItems = cartItems.reduce((total, item) => total + item.quantity || 1, 0);

	//console.log("shoppingCart ", shoppingCart);
	console.log("cartItems ", cartItems);
	console.log("totalCartItems ", totalCartItems);

	// Check if the user is signed in on page load
	const fetchUserStatus = async () => {
		try {
			// Initialize currentUser with user data
			if (await checkIfSignedIn()) {
				const userData = await checkIfSignedIn();
				console.log(userData);
				setCurrentUser(userData);
				console.log("userData", userData); // note that userData remains the same until re-login
				//console.log("refreshedUserData", refreshedUserData); // Thats why we have refreshedUserData
			} else {
				setCurrentUser(null);
			}
		} catch (error) {
			console.error("Error fetching user status:", error);
			setCurrentUser(null);
		}
	};
	useEffect(() => {
		fetchUserStatus();
	}, []);

	const handleUserChange = (event) => {
		setCurrentUser(event);
	};
	
	const refreshProfileData = async () => {
		const refreshedUserData = await refreshProfile();
		setCurrentUser(refreshedUserData);
		
	};

  return (
	  <ThemeProvider>
		<div className="App">
		<BrowserRouter>
		<NavBar currentUser={currentUser} handleUserChange={handleUserChange} handleSignout={handleSignout} ThemeContext={ThemeContext} /> 
		<Routes>
	  		<Route path="/" element={<Home />} />
		{/*{currentUser && currentUser.role === "admin" && (*/}
		{currentUser && currentUser.RoleID === 3 && (
                            <Route path="admin" element={<Admin currentUser={currentUser} />}>
                                <Route path="dashboard" element={<DashboardAdmin currentUser={currentUser} />} />
                                <Route path="users" element={<UsersAdmin currentUser={currentUser} fetchUsers={fetchUsers} />} />
								<Route path="parts" element={<PartsDisplay fetchDynamicData={fetchDynamicData} />} />
                            </Route>
		)}
		{currentUser ? (
			<>
			<Route path="profile" element={<Profile currentUser={currentUser} setCurrentUser={handleUserChange} handleCredentialChange={handleCredentialChange} handleSignout={handleSignout} checkIfSignedIn={checkIfSignedIn} />} />
			</>
		):(
			<>
			<Route path="signup" element={<Signup handleSignup={handleSignup} />} />
			<Route path="Signin" element={<Signin handleUserChange={handleUserChange} currentUser={currentUser} handleSignin={handleSignin} checkIfSignedIn={checkIfSignedIn}/>} />
			</>
		)}
			<Route path="newparts" element={<ComputerWizardBrowse fetchDynamicData={fetchDynamicData} fetchDataAmount={fetchDataAmount} currentUser={currentUser} updateDynamicData={updateDynamicData} deleteDynamicData={deleteDynamicData} />} />
			
			{shoppingCart && totalCartItems && totalCartItems > 0 && (
				<Route path="shoppingcart" element={<ShoppingCart />} />
			)}
		</Routes>
		</BrowserRouter>
		</div>
	</ThemeProvider>
  );
}

export default App;
