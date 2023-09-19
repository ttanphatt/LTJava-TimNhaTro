import { useContext, useState } from "react";
import { Alert, Button, Container, Form } from "react-bootstrap";
import Apis, { endpoints } from "../configs/Apis";
import cookie from "react-cookies";
import { authApi } from "../configs/Apis";
import { MyUserContext } from "../App";
import { Navigate } from "react-router-dom";
import "../layout/style.scss"
import DangNhap2 from "../DangNhap2";

const DangNhap = () => {
    const [user, dispatch] = useContext(MyUserContext);
    const [tenTaiKhoan, setTenTaiKhoan] = useState();
    const [matKhau, setMatKhau] = useState();
    // const [pass, setPass] = useState(true);
    const [validated, setValidated] = useState(false);
    

    const dangnhap = (evt) => {
        const Form = evt.currentTarget;
        evt.preventDefault();
        if (Form.checkValidity() === false) {
            evt.stopPropagation();
        }
        setValidated(true);

        const process = async () => {
            try {
                let res = await Apis.post(endpoints['dangnhap'],
                    {
                        "tenTaiKhoan": tenTaiKhoan,
                        "matKhau": matKhau,
                        // "kiemDuyet": kiemDuyet
                    })

                cookie.save("token", res.data);

                let { data } = await authApi().get(endpoints['current-user']);
                cookie.save("user", data);
                console.info(data);

                dispatch({
                    "type": "login",
                    "payload": data
                })
            } catch (ex) {
                console.error(ex);
            }
        }
        process();

    }


    if (user !== null)
        return <Navigate to="/" />
    return <>
        <Container className="container-dangnhap">
            <div className="form-dangnhap">
                <h1 className="text-center text-info">ĐĂNG NHẬP NGƯỜI DÙNG</h1>
                <Form noValidate validated={validated} onSubmit={dangnhap}>
                    <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                        <Form.Label>Tên đăng nhập</Form.Label>
                        <Form.Control
                            required
                            value={tenTaiKhoan}
                            onChange={e => setTenTaiKhoan(e.target.value)}
                            type="text"
                            placeholder="Tên đăng nhập" />
                        <Form.Control.Feedback type="invalid">
                            Vui lòng nhập tên đăng nhập!!!
                        </Form.Control.Feedback>
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="exampleForm.ControlInput2">
                        <Form.Label>Mật khẩu</Form.Label>
                        <Form.Control
                            required
                            value={matKhau}
                            onChange={e => setMatKhau(e.target.value)}
                            type="password"
                            placeholder="Mật khẩu" />
                        <Form.Control.Feedback type="invalid">
                            Vui lòng nhập mật khẩu!!!
                        </Form.Control.Feedback>
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="exampleForm.ControlInput3">
                        <Button variant="info" type="submit">Đăng nhập</Button>
                    </Form.Group>


                </Form>
            </div>
        </Container>
    </>

}
export default DangNhap;