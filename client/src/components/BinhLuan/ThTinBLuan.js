import { useContext, useRef, useState } from "react";
import { MyUserContext } from "../../App";
import Apis, { endpoints } from "../../configs/Apis";
import { Button, Col, Form, Image, InputGroup, Row } from "react-bootstrap";
import { Link } from "react-router-dom";
import './BinhLuan.scss';



const ThTinBLuan = (props, { replyId = 0 }) => {
    const [user] = useContext(MyUserContext);
    const canReply = Boolean(user)
    const canEdit = user.id === props.cmt.idNguoiDung.id;
    const canDelete = user.id === props.cmt.idNguoiDung.id;
    const isReplying = props.activeComment
        && props.activeComment.type === 'replying'
        && props.activeComment.id === props.cmt.id;
    const isEditing = props.activeComment
        && props.activeComment.type === 'editing'
        && props.activeComment.id === props.cmt.id;
    const hoiDap = replyId ? replyId : props.cmt.id;
    let [noiDungReply, setNoiDungReply] = useState('');
    const [noiDungEdit, setNoiDungEdit] = useState(props.cmt.noiDung);
    const commentsRef = useRef([]);

    const submitReplyComment = async (event) => {
        event.preventDefault();
        // console.log(props.cmt.userId.username);
        try {
            let res = await Apis.post(endpoints['thembl'], {
                "noiDung": noiDungReply,
                "idNguoiDung": user,
                "idBaiViet": props.cmt.idBaiViet,
                "hoiDap": hoiDap
            })
            // console.log(res);
            props.setListCmt([...props.listComment, res.data]);
            props.setActiveCmt(null);
            commentsRef.current = props.listComment;
        } catch (ex) {
            console.error(ex);
        }
        // setListCmt([...listComment, cmt])
        setNoiDungReply("");

    };

    const updateComment = (e, commentId) => {
        e.preventDefault();
        const process = async () => {
            // console.log(">>>>>>>> bắt đầu");
            const data = await Apis.post(endpoints['capnhatbl'](commentId), {
                "id": commentId,
                "noiDung": noiDungEdit,
            });
            console.log(data);
            props.setListCmt(listComment => {
                const updatedListComment = listComment.map(cmt => {
                    if (cmt.id === commentId) {
                        return { ...cmt, noiDung: noiDungEdit };
                    }
                    return cmt;
                });
                return updatedListComment;
            });
            console.log(props.listComment);
        };
        process();
        props.setActiveCmt(null);
    }

    if (props.listCmtReplies === null)
        return (<div>Chưa có bình luận</div>)

    // console.log(props.cmt.id);

    return (
        <>
            <Row className="vh-500 d-flex justify-content-center align-items-center">

                {/* style={{ borderLeft: '1px solid black' }} */}
                <ul key={props.cmt.id} className='form-comment' >
                    <li>
                        <Row>
                            <Col>
                                <Image src={props.cmt.idNguoiDung.avatar} roundedCircle style={{ width: 50, height: 50, borderRadius: 50 / 2 }} />
                            </Col>
                        </Row>
                    </li>
                    <li>
                        <div key={props.cmt.idNguoiDung.id}>@{props.cmt.idNguoiDung.tenTaiKhoan}</div>
                        {!isEditing &&
                            <>
                                <div>{props.cmt.noiDung}</div>
                                <div>
                                    {canReply && <Link
                                        className='btn_Comment'
                                        variant="primary"
                                        type='button'
                                        onClick={() => props.setActiveCmt({ id: props.cmt.id, type: "replying" })}
                                    >
                                        Trả lời
                                    </Link>}
                                    {canEdit && <Link
                                        className='btn_Comment'
                                        variant="primary"
                                        type='button'
                                        // onClick={() => props.setActiveCmt({ id: props.cmt.id, type: "editing" })}
                                        onClick={() => {
                                            props.setActiveCmt({ id: props.cmt.id, type: "editing" })
                                        }}
                                    >
                                        Chỉnh sửa
                                    </Link>}
                                    {canDelete && <Link
                                        id='button-delete-comment'
                                        className='btn_Comment '
                                        variant="primary"
                                        type='button'
                                        onClick={() => props.deleteComment(props.cmt.id)}
                                    >
                                        Xóa
                                    </Link>}
                                    {isReplying && (
                                        <Form onSubmit={submitReplyComment}>
                                            <>
                                                <ul className='form-comment'>
                                                    <li>
                                                        <Row>
                                                            <Col>
                                                                <Image src={user.avatar} roundedCircle style={{ width: 50, height: 50, borderRadius: 50 / 2 }} />
                                                            </Col>
                                                            {/* <Image style={{ width: "100%" }} src={user.avatar} roundedCircle alt='Logo' /> */}
                                                        </Row>
                                                    </li>
                                                    <li>
                                                        <InputGroup>
                                                            @{user.tenTaiKhoan}
                                                            <Form.Control
                                                                as="textarea"
                                                                aria-label="With textarea"
                                                                value={noiDungReply}
                                                                onChange={e => setNoiDungReply(e.target.value)}
                                                                placeholder='Nhập bình luận ...'>
                                                                {props.cmt.idNguoiDung.tenTaiKhoan}
                                                            </Form.Control>
                                                        </InputGroup>
                                                    </li>
                                                    <li>
                                                        <Button
                                                            variant="primary"
                                                            type="submit">
                                                            Bình luận
                                                        </Button>
                                                        
                                                    </li>
                                                    <li>
                                                        
                                                        <Button
                                                            // style={{ display: 'inline' }}
                                                            // style={{ marginRight: 10 }}
                                                            // inline={value.toString()}
                                                            variant="primary"
                                                            type="button"
                                                            onClick={() => props.setActiveCmt(null)}
                                                        >
                                                            Hủy
                                                        </Button>
                                                    </li>
                                                </ul>
                                            </>
                                        </Form >
                                    )}
                                    <>
                                        {props.listCmtReplies.map(cmtReply => (
                                            <ThTinBLuan
                                                key={cmtReply.id}
                                                cmt={cmtReply}
                                                getReplies={props.getReplies}
                                                listCmtReplies={props.getReplies(cmtReply.id)}
                                                updateComment={props.updateComment}
                                                setListCmt={props.setListCmt}
                                                deleteComment={props.deleteComment}
                                                listComment={props.listComment}
                                                activeComment={props.activeComment}
                                                setActiveCmt={props.setActiveCmt}
                                                reply={props.cmt.id}
                                            />
                                        ))}
                                    </>
                                </div>
                            </>}
                        {isEditing &&
                            <Form onSubmit={(e) => updateComment(e, props.cmt.id)} >
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
                                                value={noiDungEdit}
                                                onChange={e => setNoiDungEdit(e.target.value)}
                                                placeholder='Nhập bình luận ... ' />
                                        </InputGroup>
                                    </li>
                                    <li>
                                        <Button
                                            variant="primary"
                                            type="submit">
                                            Cập nhật
                                        </Button>
                                        {/* {handleCancelButton && ( */}
                                        <Button
                                            variant="primary"
                                            type='button'
                                            // className='comment-form-button comment-form-cancel-button'
                                            onClick={() => props.setActiveCmt(null)} >
                                            Hủy
                                        </Button>
                                        {/* )} */}
                                    </li>
                                </ul>
                            </Form>
                        }
                    </li>
                </ul >
            </Row>
        </>
    );
};

export default ThTinBLuan;