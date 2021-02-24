let index = {
	init: function() {
		$("#btn-save").on("click", () => { //function(){} , ()=>{} this를 바인딩하기 위해서!!
			this.save();
		});
		/*$("#btn-login").on("click", () => { //function(){} , ()=>{} this를 바인딩하기 위해서!!
			this.login();
		});*/
	},

	save: function() {
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		console.log(data);
		//ajax호출시 default = 비동기
		$.ajax({
			//회원가입 수행 요청
			type:"POST",
			url:"/auth/joinProc",
			data:JSON.stringify(data), //http body 데이터
			contentType:"application/json;charset=utf-8", //body 데이터 타입(MIME)
			dataType:"json" //응답 타입 , 기본적으로 문자열이나 json 형태면 json형식으로 응답
		}).done(function(resp) {
			alert("회원가입이 완료되었습니다.");
			//console.log(resp);
			location.href = "/";
		}).fail(function(error) {
			alert(JSON,stringify(error));
		});
	},
	
/*	login: function() {
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
		}
		console.log(data);
		//ajax호출시 default = 비동기
		$.ajax({
			//회원가입 수행 요청
			type:"POST",
			url:"/api/user/login",
			data:JSON.stringify(data), //http body 데이터
			contentType:"application/json;charset=utf-8", //body 데이터 타입(MIME)
			dataType:"json" //응답 타입 , 기본적으로 문자열이나 json 형태면 json형식으로 응답
		}).done(function(resp) {
			alert("로그인이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON,stringify(error));
		});
	}*/
}

index.init();