// File name: Signup.js
// Auth: Terminal Swag Disorder
// Desc: File containing code for signing up page
import React, { useState, useEffect } from "react";
import { Container, Button, Form, Spinner, OverlayTrigger, Tooltip } from "react-bootstrap";
import { useNavigate } from "react-router-dom";


// Function for rendering sign up page, takes onSubmit as a prop
export const Signup = ({ handleSignup }) => {
	const navigate = useNavigate();
	const [isLoading, setIsLoading] = useState(false);
	const [formFields, setFormFields] = useState(
		{
		name: "",
		email: "",
		password: "",
		});
	const [inputValue, setInputValue] = useState("");
	const [emailValid, setEmailValid] = useState(false);
	const [passwordValid, setPasswordValid] = useState(false);

	const passwordRegex = /^(?=.*[A-Z])(?=.*\d)[A-Za-z\d]{9,}$/;
	const emailRegex = /^[-A-Za-z0-9!#$%&'*+/=?^_`{|}~]+(?:\.[-A-Za-z0-9!#$%&'*+/=?^_`{|}~]+)*@(?:[A-Za-z0-9](?:[-A-Za-z0-9]*[A-Za-z0-9])?\.)+[A-Za-z0-9](?:[-A-Za-z0-9]*[A-Za-z0-9])?$/;

	const handleInputChange = (event) => {
		setInputValue(event.target.value);
		setFormFields((prevFields) => ({
			...prevFields,
			[event.target.name]: event.target.value,
		}));

		if (event.target.name === "email") {
			setEmailValid(emailRegex.test(event.target.value));
		}

		if (event.target.name === "password") {
			setPasswordValid(passwordRegex.test(event.target.value));
		}
	};
		  
  // Function for when the user submits the sign up form
	const handleSubmit = async (event) => {
		const newName = event.target.name.value;
		const newEmail = event.target.email.value;
		const newPassword = event.target.password.value;
		if (!newName && !newEmail && !newPassword) {
			alert("All required fields must be filled!");
			return;
		}

		// Need this to prevent regular js from ruining the form submission
		event.preventDefault();
		setIsLoading(true);
		try {
			const success = await handleSignup(event, formFields);
			if (success) {
				navigate("/signin");
				setFormFields({});
				setEmailValid(false);
				setPasswordValid(false);
			}


			} catch (error) {
				console.log(error.message);
			} finally {
				setIsLoading(false);
			}
		};

	const renderTooltip = (props) => (
		<Tooltip id="button-tooltip" {...props}>
			Password must be at least 9 characters long, include 1 capital letter, and 1 number.
		</Tooltip>
	);
	
	const renderSignupForm = () => {
		return (
			<div>
				<Container
					style={{
						display: "flex",
						justifyContent: "center",
						alignItems: "center",
						minHeight: "90vh"
					}}>
					<div
						style={{
							width: "100%",
							maxWidth: "400px",
							padding: "20px",
							borderRadius: "8px",
							boxShadow: "0 4px 8px rgba(0,0,0,0.1)"
						}}>
						<Form style={{ textAlign: "left" }} onSubmit={handleSubmit}>
							<h1>Sign up</h1>

							<Form.Group className="mb-3">
								<Form.Label>Name</Form.Label>
								<Form.Control
									type="text"
									placeholder="Enter name"
									required
									name="name"
									value={formFields.name}
									onChange={handleInputChange}
								/>
							</Form.Group>

							<Form.Group className="mb-3" controlId="formBasicEmail">
								<Form.Label>Email address</Form.Label>
								<Form.Control
									type="email"
									placeholder="Enter email"
									required
									name="email"
									value={formFields.email}
									onChange={handleInputChange}
									className={emailValid ? "valid-input" : "invalid-input"}
								/>
							</Form.Group>

							<Form.Group className="mb-3" controlId="formBasicPassword">
								<Form.Label>Password</Form.Label>
								<OverlayTrigger placement="right" delay={{ hide: 400 }} overlay={renderTooltip}>
									<Form.Control
										type="password"
										placeholder="Enter password"
										required
										name="password"
										value={formFields.password}
										onChange={handleInputChange}
										className={passwordValid ? "valid-input" : "invalid-input"}
									/>
								</OverlayTrigger>
							</Form.Group>

							<Button type="submit" style={{ width: "100%" }} disabled={isLoading}>
								{isLoading ? (
									<>
										<Spinner as="span" animation="border" size="sm" role="status" aria-hidden="true" />
										<span className="visually-hidden">Loading...</span>
									</>
								) : (
									"Sign up"
								)}
							</Button>
						</Form>
					</div>
				</Container>
			</div>
		);
	};
	
  return (
	  <>
	  {renderSignupForm()}
	  </>
  );
};

export default Signup;

