import { useEffect, useState, useContext } from "react";
import Container from "react-bootstrap/Container";
import { Nav, Navbar, NavDropdown, Button, Image } from "react-bootstrap";
import { Link } from "react-router-dom";
import { AiOutlineShoppingCart } from "react-icons/ai";
import { useSelector, useDispatch } from "react-redux";

const NavBar = ({ currentUser, handleUserChange, handleSignout, ThemeContext }) => {
	const [activeLink, setActiveLink] = useState("home");
	const [scrolled, setScrolled] = useState(false);
	const [showDropdown, setShowDropdown] = useState(false);
	const { theme, toggleTheme } = useContext(ThemeContext);
	const shoppingCart = useSelector((state) => state.shoppingCart.shoppingCart);
	const dispatch = useDispatch();
	const cartItems = Object.values(shoppingCart);
  	const totalCartItems = cartItems.reduce((total, item) => total + item.quantity || 1, 0);

	useEffect(() => {
		const onScroll = () => {
			if (window.scrollY > 50) {
				setScrolled(true);
			} else {
				setScrolled(false);
			}
		};

		window.addEventListener("scroll", onScroll);

		return () => window.removeEventListener("scroll", onScroll);
	}, []);

	const onUpdateActiveLink = (value) => {
		setActiveLink(value);
	};

	// Async function for signout
	const handleLogout = async () => {
		try {
			await handleSignout();
			handleUserChange(null);
		} catch (error) {
			console.log(error.message);
		}
	};

	const toggleDropdownShow = (value) => {
		setShowDropdown(value);
	};
	const toggleDropdownHide = (event) => {
		setShowDropdown(false);
	};

	const shoppingCartNavbar = () => {
		if (totalCartItems || totalCartItems > 0) {
			return (
				<Nav.Link as={Link} to="/shoppingcart" className={activeLink === "/shoppingcart" ? "active-navbar-link" : "navbar-link"} onClick={() => onUpdateActiveLink("/shoppingcart")}>
					View Cart <AiOutlineShoppingCart /> {totalCartItems}
				</Nav.Link>
			);
		}
	};

	
	const userNavbar = () => {
		let adminCheck;
		let userCheck;
		if (currentUser && currentUser.role !== "user") {
			adminCheck = (
				<>
					<Nav.Link as={Link} to="/admin/dashboard" className={activeLink === "/admin/dashboard" ? "active-navbar-link" : "navbar-link"} onClick={() => onUpdateActiveLink("/admin/dashboard")}>
						Dashboard
					</Nav.Link>
				</>
			);
		}
		if (currentUser) {
			userCheck = (
				<>
					<Nav.Link as={Link} to="/profile">
						{currentUser.Name}
					</Nav.Link>
					<Nav.Link as={Link} to="/" onClick={handleLogout}>
						Log out
					</Nav.Link>
				</>
			);
		} else {
			userCheck = (
				// If false do this
				<>
					<Nav.Link as={Link} to="/signin" className={activeLink === "/signin" ? "active-navbar-link" : "navbar-link"} onClick={() => onUpdateActiveLink("/signin")}>
						Not signed in
					</Nav.Link>
					<Nav.Link as={Link} to="/signup" className={activeLink === "/signup" ? "active-navbar-link" : "navbar-link"} onClick={() => onUpdateActiveLink("/signup")}>
						Signup
					</Nav.Link>
				</>
			);
		}
		return (
			<>
				{userCheck}
				{adminCheck}
			</>
		);
	};

	return (
		<Navbar expand="md" className={scrolled ? "scrolled" : ""}>
			<Container>
				<Navbar.Brand as={Link} to="/" onClick={() => onUpdateActiveLink("/")}>
					KoneAvustajat
				</Navbar.Brand>
				<Button className="themeSwitcher" onClick={toggleTheme}>
					Switch to {theme === "light" ? "Dark" : "Light"} Mode
				</Button>
				<Navbar.Toggle aria-controls="basic-navbar-nav">
					<span className="navbar-toggler-icon"></span>
				</Navbar.Toggle>
				<Navbar.Collapse id="basic-navbar-nav">
					<Nav className="mx-auto">
						<Nav.Link
							as={Link}
							to="/"
							className={activeLink === "/" ? "active-navbar-link" : "navbar-link"}
							onClick={() => onUpdateActiveLink("/")}>
							Home
						</Nav.Link>
						<Nav.Link
							as={Link}
							to="/newparts"
							className={
									activeLink === "/newparts" ? "active-navbar-link" : "navbar-link"
								}
							onClick={() => onUpdateActiveLink("/newparts")}>
							Browse parts
						</Nav.Link>
					</Nav>
					<Nav className="ml-auto">{userNavbar()}</Nav>
					<Nav className="ml-auto">{shoppingCartNavbar()}</Nav>

				</Navbar.Collapse>
			</Container>
		</Navbar>
	);
};

export default NavBar;
