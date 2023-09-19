import BaiViet1 from "./BaiViet/BaiViet1";
import BaiViet2 from "./BaiViet/BaiViet2";
import "../layout/style.scss"
import { Button, Col, Container, Form, Row } from "react-bootstrap";
import { useState } from "react";
import { useNavigate } from "react-router-dom";


const TrangChu = () => {
    const [kw, setKw] = useState("");
    const nav = useNavigate();
    const search = (evt) => {
        evt.preventDefault();
        nav(`/search?kw=${kw}`);

    }
    return (
        <>
            <Container className="trangchu">

                <div className="trangchu-search">
                    <Form className="header-search" onSubmit={search} inline>
                        <Row>
                            <Col xs="auto">
                                <Form.Control
                                    type="text"
                                    value={kw}
                                    onChange={e => setKw(e.target.value)}
                                    placeholder="Nhập từ khóa..." name="kw"
                                    className=" mr-sm-2"
                                />
                            </Col>
                            <Col xs="auto">
                                <Button variant="info" className='buttonTim' type='submit'> Tìm </Button>
                            </Col>
                        </Row>
                    </Form>
                </div>
                <div className="trangchu-baiviet">
                    <div className="trangchu-baiviettheoloai">
                        <BaiViet1 />
                        
                        <BaiViet2 />
                    </div>
                </div>
            </Container>

        </>

    )
}

export default TrangChu;