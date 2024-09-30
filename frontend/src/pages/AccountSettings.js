import React, { useEffect, useState } from 'react';
import { useDispatch } from 'react-redux';
import { callUserDetailAPI, callModifyUser } from '../apis/UserAPICalls';
import { jwtDecode } from 'jwt-decode'; 

const AccountSettings = () => {
    const dispatch = useDispatch();
    const [user, setUser] = useState({});
    const [formData, setFormData] = useState({
        userNickname: '',
        userGender: '',
    });

    const token = localStorage.getItem('jwtToken');
    const decodedToken = jwtDecode(token);
    const userEmail = decodedToken.email;

    useEffect(() => {
        dispatch(callUserDetailAPI(userEmail))
            .then(data => {
                setUser(data.results.user);
                setFormData({
                    userNickname: data.results.user.userNickname,
                    userGender: data.results.user.userGender,
                });
            })
            .catch(error => {
                console.error('API 호출 중 오류 발생:', error);
            });
    }, [dispatch, userEmail]);

    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData(prevState => ({
            ...prevState,
            [name]: value,
        }));
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        dispatch(callModifyUser(userEmail, formData))
            .then(data => {
                console.log('회원 정보 수정 완료:', data);
                callUserDetailAPI();
                alert('성공적으로 변경 되었습니다.');
            })
            .catch(error => {
                console.error('회원 정보 수정 오류 발생:', error);
            });
    };

    const handleDeleteAccount = (event) => {
        event.preventDefault();
        // 회원 탈퇴 로직
    };

    return (
        <div className="flex flex-col flex-auto h-full p-6">
            <div className="col-md-12">
                <div className="card mb-4">
                    <h5 className="card-header">Profile Details</h5>
                    <div className="card-body">
                        <div className="d-flex align-items-start align-items-sm-center gap-4">
                            <img
                                src="../img/1.png"
                                alt="user-avatar"
                                className="d-block rounded"
                                height="100"
                                width="100"
                                id="uploadedAvatar"
                            />
                            <div className="button-wrapper">
                                <label htmlFor="upload" className="btn btn-primary me-2 mb-4" tabIndex="0">
                                    <span className="d-none d-sm-block">Upload new photo</span>
                                    <i className="bx bx-upload d-block d-sm-none"></i>
                                    <input type="file" id="upload" className="account-file-input" hidden accept="image/png, image/jpeg" />
                                </label>
                                <button type="button" className="btn btn-outline-secondary account-image-reset mb-4">
                                    <i className="bx bx-reset d-block d-sm-none"></i>
                                    <span className="d-none d-sm-block">Reset</span>
                                </button>
                                <p className="text-muted mb-0">Allowed JPG, GIF or PNG. Max size of 800K</p>
                            </div>
                        </div>
                    </div>
                    <hr className="my-0" />
                    <div className="card-body">
                        <form id="formAccountSettings" onSubmit={handleSubmit}>
                            <div className="row">
                                {[
                                    { label: 'Name', id: 'name', type: 'text', placeholder: user.userName, disabled: true },
                                    { label: 'Nickname', id: 'nickname', type: 'text', placeholder: user.userNickname },
                                    { label: 'Email', id: 'email', type: 'email', placeholder: user.userEmail, disabled: true },
                                    { label: 'Gender', id: 'gender', type: 'text', placeholder: user.userGender },
                                ].map((input, index) => (
                                    <div className="mb-3 col-12" key={index}>
                                        <label htmlFor={input.id} className="form-label">{input.label}</label>
                                        <input
                                            className="form-control"
                                            type={input.type}
                                            id={input.id}
                                            name={input.id === 'nickname' ? 'userNickname' : input.id === 'gender' ? 'userGender' : ''}
                                            placeholder={input.placeholder}
                                            value={input.id === 'nickname' ? formData.userNickname : input.id === 'gender' ? formData.userGender : ''}
                                            onChange={handleChange}
                                            disabled={input.disabled}
                                        />
                                    </div>
                                ))}
                            </div>
                            <div className="mt-2">
                                <button type="submit" className="btn btn-primary me-2">변경사항 저장</button>
                                <button type="reset" className="btn btn-outline-secondary">취소</button>
                            </div>
                        </form>
                    </div>
                </div>
                <div className="card">
                    <h5 className="card-header">Delete Account</h5>
                    <div className="card-body">
                        <div className="mb-3 col-12 mb-0">
                            <div className="alert alert-warning">
                                <h6 className="alert-heading fw-bold mb-1">회원 탈퇴 하시겠습니까 ?</h6>
                                <p className="mb-0">회원 탈퇴를 하시게 되면, 회원의 정보가 전부 사라지게 됩니다.</p>
                            </div>
                        </div>
                        <form id="formAccountDeactivation" onSubmit={handleDeleteAccount}>
                            <div className="form-check mb-3">
                                <input className="form-check-input" type="checkbox" name="accountActivation" id="accountActivation" />
                                <label className="form-check-label" htmlFor="accountActivation">회원 탈퇴</label>
                            </div>
                            <button type="submit" className="btn btn-danger deactivate-account">회원 탈퇴</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default AccountSettings;
