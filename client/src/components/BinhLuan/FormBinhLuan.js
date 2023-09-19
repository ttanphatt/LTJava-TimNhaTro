import React, { useContext} from 'react';
import { Button, Card, Col, Form, InputGroup, Row } from 'react-bootstrap';
import { MyUserContext } from '../../App';

const FormBinhLuan = ({
    addQuestion,
    setShowForm = true,
    contentQuestion,
    setContentState,
}) => {
    const [user] = useContext(MyUserContext);
    

    return (
        <>
            <div className="overlay">
                <div className="form-container">
                    <Form onSubmit={addQuestion} >
                        <Row className="vh-100 d-flex justify-content-center ">
                            <Col md={8} lg={10} xs={12}>
                                <div className="border border-3 border-primary"></div>
                                <Card className="shadow">
                                    <Card.Body>
                                        <div className="mb-3 mt-md-4">
                                            <h2 className="fw-bold mb-2 text-uppercase text-center">Đặt câu hỏi</h2>
                                            <div className="mb-3">
                                                <button className="close-button" onClick={() => setShowForm(false)}>X</button>
                                                <Form.Group className="mb-3" controlId="formBasicEmail">
                                                    <p className=" textDky">Tên đăng nhập: {user.username}</p>
                                                </Form.Group>
                                                <li className='form-question'>
                                                    <p className=" textDky">Bạn muốn đặt câu hỏi gì</p>
                                                    <InputGroup>
                                                        <Form.Control
                                                            as="textarea"
                                                            aria-label="With textarea"
                                                            value={contentQuestion}
                                                            onChange={e => setContentState(e.target.value)}
                                                            placeholder='Nhập câu hỏi ... ' />
                                                    </InputGroup>
                                                </li>
                                                <li className='form-question form-question-btn'>
                                                    <Button
                                                        variant="primary"
                                                        type="submit">
                                                        Gửi
                                                    </Button>
                                                </li>
                                            </div>
                                        </div>
                                    </Card.Body>
                                </Card>
                            </Col>
                        </Row>
                    </Form>
                </div>
            </div>
        </>
    );
};

export default FormBinhLuan;