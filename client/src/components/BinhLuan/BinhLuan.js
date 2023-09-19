import { useContext, useEffect,useRef, useState } from 'react';
import { Button, Col, Container, Form, Image, InputGroup, Row } from 'react-bootstrap';
import './BinhLuan.scss';
import Apis, { endpoints } from '../../configs/Apis';
import ThTinBLuan from './ThTinBLuan';
import { MyUserContext } from '../../App';

const BinhLuan = (props) => {
    const [user] = useContext(MyUserContext);
    const [noiDung, setNoiDung] = useState();
    const [listComment, setListCmt] = useState([]);
    const [activeComment, setActiveCmt] = useState(null);
    const idBaiViet = props.idBaiViet;
    const hoiDap = 0;
    
    useEffect(() => {
        Apis.get(`${endpoints['binhluanBybaiviet']} + ${idBaiViet}`)
            .then(response => {
                setListCmt(response.data);
                console.log(response.data);
            })
            .catch(error => {
                console.log('Error fetching post:', error);            });

    }, [idBaiViet])
    
    const comments = listComment.filter(
        (cmt) => cmt.hoiDap === 0
    );

    const getReplies = cmtId => {
        return listComment.filter(cmt => cmt.hoiDap === cmtId)
    }

    const addComment = (e) => {
        e.preventDefault();
        console.log("data");
        const process = async () => {
            let { data } = await Apis.post(endpoints['thembl'], {
                "noiDung": noiDung,
                "idBaiViet": idBaiViet,
                "idNguoiDung": user,
                "hoiDap": hoiDap
            })
            console.log(data);
            setListCmt([data, ...listComment]);
            setNoiDung("");
        };
        process();
    }

    const deleteComment = (cmtId) => {
        if (window.confirm("Bạn có chắc chắn muốn xóa bình luận này?")) {
            Apis.delete(endpoints['xoabl'](cmtId))
                .then(() => {
                    setListCmt(listComment.filter((comment) => comment.id !== cmtId));
                });
        }
    }


    if (listComment === null)
        return (<div>Chưa có bình luận</div>)


    return (
        <>
            <Row className="vh-500 d-flex justify-content-center align-items-center">
                {/* <FormBinhLuan submitLabel="Bình luận" handleSubmit={addComment} /> */}
                <Form onSubmit={addComment}>
                    
                     <ul className='form-comment'>
                        <li>
                            <Row>
                                <Col>
                                    <Image src={user.avatar} roundedCircle style={{ width: 50, height: 50, borderRadius: 50 / 2 }} />
                                </Col>
                            </Row>
                        </li>
                        <li>
                            <InputGroup>
                                <Form.Control
                                    as="textarea"
                                    aria-label="With textarea"
                                    value={noiDung}
                                    onChange={e => setNoiDung(e.target.value)}
                                    placeholder='Nhập bình luận ... ' />
                            </InputGroup>
                        </li>
                        <li>
                            <Button
                                variant="primary"
                                type="submit">
                                Bình luận
                            </Button>
                        </li>
                    </ul> 
                </Form>
                {comments.map(cmt => (
                    <ThTinBLuan
                        key={cmt.id}
                        cmt={cmt}
                        getReplies={getReplies}
                        listCmtReplies={getReplies(cmt.id)}
                        deleteComment={deleteComment}
                        // updateComment={updateComment}
                        setListCmt={setListCmt}
                        listComment={listComment}
                        activeComment={activeComment}
                        setActiveCmt={setActiveCmt}
                    />
                ))}


            </Row >
        </>
    );
};
export default BinhLuan;