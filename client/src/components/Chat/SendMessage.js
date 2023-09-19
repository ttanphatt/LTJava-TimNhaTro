import { Container, Form } from "react-bootstrap";
import React, { useContext, useState } from 'react';
import { MyUserContext } from '../../App';
import { addDoc, collection, serverTimestamp, updateDoc, doc, where } from "firebase/firestore";
import { db } from "../../firebase"
import { Navigate, useParams } from "react-router-dom";


const SendMessage = () => {
    const [message, setMessage] = useState("");
    const [user] = useContext(MyUserContext);
    const { tenTaiKhoan } = useParams();
    console.log(user.idLoaiTaiKhoan.tenLoaiTaiKhoan);
    var url_collectionKH = "messages/" + user['tenTaiKhoan'] + "/chat"; //kh add dc
    var url_collectionAD = "messages/" + tenTaiKhoan + "/chat"; // ad add dc

    const handleSendMessage = async (e) => {
        e.preventDefault();

        if (message.trim() === "") {
            alert("Vui lòng nhập tin nhắn");
            return;
        }
        if (user.idLoaiTaiKhoan.tenLoaiTaiKhoan === "ROLE_KHACHHANG") {
            try {
                const uid = user['id']
    
                const docRef = doc(db, "messages", user['tenTaiKhoan']);
                await updateDoc(docRef, {
                    createdAt: serverTimestamp(),
                    lastMessage: message
                });
                await addDoc(collection(db, url_collectionKH), {
                    text: message,
                    tenTaiKhoan: user['tenTaiKhoan'],
                    avatar: user['avatar'],
                    createdAt: serverTimestamp(),
                    uid
                })
    
            } catch (error) {
                console.log(error);
            }
        }
        else if (user.idLoaiTaiKhoan.tenLoaiTaiKhoan === "ROLE_ADMIN") {
            try {
                const uid = user['id']

                const docRef = doc(db, "messages", tenTaiKhoan);
                await updateDoc(docRef, {
                    createdAt: serverTimestamp(),
                    lastMessage: message
                });

                await addDoc(collection(db, url_collectionAD), {
                    text: message,
                    tenTaiKhoan: user['tenTaiKhoan'],
                    avatar: user['avatar'],
                    createdAt: serverTimestamp(),
                    uid
                })
            } catch (error) {
                console.log(error);
            }
        }
        else {
            console.log("ERROR");
        }

        setMessage("");
    }

    return (
        <Container>
            <div className="w-full">
                <Form onSubmit={handleSendMessage}>
                    <div class="card-footer text-muted d-flex justify-content-start align-items-center p-3">
                        <div class="input-group mb-0">
                            <input type="text" class="form-control" placeholder="Nhập tin nhắn"
                                required value={message} onChange={e => setMessage(e.target.value)} />
                            <button class="btn btn-primary" type="submit" id="button-send">
                                Gửi
                            </button>
                        </div>
                    </div>
                </Form>
            </div>
        </Container>
    );
}

export default SendMessage;