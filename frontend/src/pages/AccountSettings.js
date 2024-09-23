import React from 'react';

const AccountSettings = () => {
    const handleSubmit = (event) => {
        event.preventDefault();
    };

    const handleDeleteAccount = (event) => {
        event.preventDefault();
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
                                    { label: 'Name', id: 'name', type: 'text', placeholder: 'Enter your name' },
                                    { label: 'Nickname', id: 'nickname', type: 'text', placeholder: 'Enter your nickname' },
                                    { label: 'Email', id: 'email', type: 'email', placeholder: 'john.doe@example.com' },
                                    { label: 'Gender', id: 'gender', type: 'text', placeholder: 'Enter your gender' },
                                ].map((input, index) => (
                                    <div className="mb-3 col-12" key={index}>
                                        <label htmlFor={input.id} className="form-label">{input.label}</label>
                                        <input
                                            className="form-control"
                                            type={input.type}
                                            id={input.id}
                                            name={input.id}
                                            placeholder={input.placeholder}
                                        />
                                    </div>
                                ))}
                            </div>
                            <div className="mt-2">
                                <button type="submit" className="btn btn-primary me-2">Save changes</button>
                                <button type="reset" className="btn btn-outline-secondary">Cancel</button>
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
