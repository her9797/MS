function Card() {
    return (
        <div class="row gx-4 gx-lg-5">
                <div class="col-md-4 mb-5">
                    <div class="card h-100">
                        <div class="card-body">
                            <h2 class="card-title">Card One</h2>
                            <p class="card-text">이거 백엔드랑 연동될 것</p>
                        </div>
                        <div class="card-footer"><a class="btn btn-primary btn-sm" href="#!">상세보기</a></div>
                    </div>
                </div>
                <div class="col-md-4 mb-5">
                    <div class="card h-100">
                        <div class="card-body">
                            <h2 class="card-title">Card Two</h2>
                            <p class="card-text">메인에서 카드는 3개만 보일 것임</p>
                        </div>
                        <div class="card-footer"><a class="btn btn-primary btn-sm" href="#!">상세보기</a></div>
                    </div>
                </div>
                <div class="col-md-4 mb-5">
                    <div class="card h-100">
                        <div class="card-body">
                            <h2 class="card-title">Card Three</h2>
                            <p class="card-text">notice가 될 것 같음</p>
                        </div>
                        <div class="card-footer"><a class="btn btn-primary btn-sm" href="#!">상세보기</a></div>
                    </div>
                </div>
            </div>
    );
}

export default Card;