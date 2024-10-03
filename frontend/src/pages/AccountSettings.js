import React, { useEffect, useState } from 'react';
import { useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { callUserDetailAPI, callModifyUser } from '../apis/UserAPICalls';
import { jwtDecode } from 'jwt-decode'; 
import ConfirmationModal from '../components/common/ConfirmationModal.js';

const AccountSettings = () => {
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const [user, setUser] = useState(null);
    const [formData, setFormData] = useState({
        userNickname: '',
        userGender: '',
    });
    const [loading, setLoading] = useState(true);
    const [showModal, setShowModal] = useState(false); // 모달 상태 관리
    const [confirmDelete, setConfirmDelete] = useState(false); // 체크박스 상태 관리

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
                setLoading(false);
            })
            .catch(error => {
                console.error('API 호출 중 오류 발생:', error);
                setLoading(false);
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
                alert('성공적으로 변경 되었습니다.');
            })
            .catch(error => {
                console.error('회원 정보 수정 오류 발생:', error);
            });
    };

    const handleDeleteAccount = () => {
        dispatch(callModifyUser(userEmail, { userStatus: 'INACTIVE' }))
            .then(data => {
                console.log('회원 탈퇴 완료:', data);
                alert('회원 탈퇴 성공');
                localStorage.removeItem('jwtToken');
                navigate('/login');
            })
            .catch(error => {
                console.error('회원 탈퇴 오류 발생:', error);
            });
        setShowModal(false); // 모달 닫기
    };

    const handleShowModal = () => {
        if (confirmDelete) {
            setShowModal(true); // 체크박스가 선택된 경우에만 모달 열기
        } else {
            alert('회원 탈퇴를 원하시면 체크박스를 선택하세요.');
        }
    };

    const handleCloseModal = () => setShowModal(false); // 모달 닫기

    if (loading) {
        return <div>Loading...</div>;
    }

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
                                    { label: 'Nickname', id: 'nickname', type: 'text', placeholder: '닉네임', name: 'userNickname' },
                                    { label: 'Email', id: 'email', type: 'email', placeholder: user.userEmail, disabled: true },
                                    { label: 'Gender', id: 'gender', type: 'select', placeholder: '성별 선택', name: 'userGender' },
                                ].map((input, index) => (
                                    <div className="mb-3 col-12" key={index}>
                                        <label htmlFor={input.id} className="form-label">{input.label}</label>
                                        {input.type === 'select' ? (
                                            <select
                                                className="form-control"
                                                id={input.id}
                                                name={input.name}
                                                value={formData.userGender}
                                                onChange={handleChange}
                                            >
                                                <option value="">성별 선택</option>
                                                <option value="남">남</option>
                                                <option value="여">여</option>
                                            </select>
                                        ) : (
                                            <input
                                                className="form-control"
                                                type={input.type}
                                                id={input.id}
                                                name={input.name}
                                                placeholder={input.placeholder}
                                                value={input.name === 'userNickname' ? formData.userNickname : ''}
                                                onChange={handleChange}
                                                disabled={input.disabled}
                                            />
                                        )}
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
                        <div className="form-check mb-3">
                            <input
                                className="form-check-input"
                                type="checkbox"
                                id="confirmDelete"
                                checked={confirmDelete}
                                onChange={() => setConfirmDelete(prev => !prev)}
                            />
                            <label className="form-check-label" htmlFor="confirmDelete">
                                회원 탈퇴 확인
                            </label>
                        </div>
                        <button type="button" className="btn btn-danger" onClick={handleShowModal}>회원 탈퇴</button>
                    </div>
                </div>
            </div>

            {/* 분리된 모달 컴포넌트 */}
            <ConfirmationModal
                show={showModal}
                onClose={handleCloseModal}
                onConfirm={handleDeleteAccount}
                message="정말로 회원 탈퇴를 하시겠습니까? 이 작업은 되돌릴 수 없습니다."
            />
        </div>
    );
};

export default AccountSettings;
