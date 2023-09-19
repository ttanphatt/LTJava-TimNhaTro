import { useContext, useEffect, useState } from "react";
import { Button, Col, Container, Form, Image, Nav, Navbar, Row } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import { MyUserContext } from "../App";
import "./style.scss";
import Apis, { endpoints } from "../configs/Apis";

const Header = () => {
    const [user, dispatch] = useContext(MyUserContext);
    // const [kw, setKw] = useState("");
    // const nav = useNavigate();

    // const search = (evt) => {
    //     evt.preventDefault();
    //     nav(`/search?kw=${kw}`);
    // }

    const logout = () => {
        dispatch({
            "type": "logout",
        })
    }
    return (
        <>
            <div className='header'>
                <Navbar style={{ color: 'white' }} expand="lg" className="header">
                    <div className="nav-title">
                        {/* <div><Navbar.Brand href="#home" className="brand">
                            <img
                                src="https://res.cloudinary.com/dpp5kyfae/image/upload/v1694192001/_e4154a4b-519b-4344-b0a5-5baa8b22e03a_wqccd6.jpg"
                                width="50"
                                height="50"
                                className="d-inline-block align-top"
                                alt="React Bootstrap logo"
                            />
                        </Navbar.Brand>
                        </div> */}
                        <div>  <Nav style={{ color: 'white', alignItems: 'center' }} className="mx-auto" href="/">MY WEBSITE</Nav></div>
                    </div>
                    <div className="nav-links">
                        <Nav style={{ display: 'flex' }} className="me-auto">
                            <Link style={{ color: 'white' }} to="/" className="nav-link">TRANG CHỦ</Link>
                            {(user === null) ? <>
                                <Link style={{ color: 'white' }} to="/dangnhap" className="nav-link">ĐĂNG NHẬP</Link>
                                <Link style={{ color: 'white' }} to="/dangky" className="nav-link">ĐĂNG KÍ</Link>
                            </> : <>
                                <Link style={{ color: 'white' }} className="nav-link" to={`/thtin-ngdung/${user.id}`}>TRANG CÁ NHÂN</Link>
                                <Link style={{ color: 'white' }} className="nav-link" to="/dangbai/">ĐĂNG BÀI</Link>
                                <Link style={{ color: 'white' }} className="nav-link" to="/chat">CHAT</Link>
                                {/* <Link to="/bando">BAN DO</Link> */}
                            </>
                            }
                        </Nav>
                    </div>
                    <div className="nav-user">
                        {(user === null) ? <>
                        </> : <>
                            <div className="hellouser mx-auto">
                                <Link style={{ color: 'white', margin:'auto 0', marginRight:'20px' }} className="nav-link mx-auto" to="/">Chào {user.tenTaiKhoan} </Link>
                                <Image roundedCircle style={{ width: 40, height: 40, borderRadius: 40 / 2 }} src={user.avatar}></Image>
                            </div>                            
                                <Button style={{ color: 'white', height: '50%' }} variant="secondary" onClick={logout}>
                                    <Link className="logout" to="/">Đăng xuất</Link>
                                </Button>
                            
                        </>
                        }
                    </div>
                </Navbar >
            </div >
        </>




        // <Navbar expand="lg" className="bg-body-tertiary header">
        //     <Container className="header" >
        //         <Navbar.Brand href="#home">
        //             <img
        //                 src="https://res.cloudinary.com/dpp5kyfae/image/upload/v1694192001/_e4154a4b-519b-4344-b0a5-5baa8b22e03a_wqccd6.jpg"
        //                 width="50"
        //                 height="50"
        //                 className="d-inline-block align-top"
        //                 alt="React Bootstrap logo"
        //             />
        //         </Navbar.Brand>
        //         <Navbar.Brand href="#home">MY WEBSITE</Navbar.Brand>
        //         <Navbar.Toggle aria-controls="basic-navbar-nav" />
        //         <Navbar.Collapse id="basic-navbar-nav">
        //             <Nav style={{ display: 'flex' }} className="me-auto header">
        //                 <Link to="/" className="nav-link">TRANG CHỦ</Link>

        //                 {(user === null) ? <>
        //                     <Link to="/dangnhap" className="nav-link text-danger">ĐĂNG NHẬP</Link>
        //                     <Link to="/dangky" className="nav-link text-danger">ĐĂNG KÍ</Link>
        //                 </> : <>
        //                     <Link className="nav-link" to="/">Chào {user.tenTaiKhoan}</Link>
        //                     <Image roundedCircle style={{ width: 40, height: 40, borderRadius: 40 / 2 }} className="nav-link" src={user.avatar}></Image>
        //                     <Link className="nav-link" to={`/thtin-ngdung/${user.id}`}>TRANG CÁ NHÂN</Link>
        //                     <Link className="nav-link" to="/dangbai/">ĐĂNG BÀI</Link>
        //                     <Link className="nav-link" to="/chat">CHAT</Link>
        //                     {/* <Link to="/bando">BAN DO</Link> */}
        //                     <Button variant="secondary" style={{ height: '50%' }} onClick={logout}><Link className="logout" to="/">Đăng xuất</Link></Button>
        //                 </>
        //                 }

        //             </Nav>
        //         </Navbar.Collapse>
        //         
        //     </Container>
        // </Navbar>
    )
}
export default Header;