let index = {
	init: function() {
		$("#save").on("click", () => { //function(){} , ()=>{} this를 바인딩하기 위해서!!
			this.save();
		});
		$("#btn-delete").on("click", () => { //function(){} , ()=>{} this를 바인딩하기 위해서!!
			this.deleteById();
		});
		$("#btn-update").on("click", () => { //function(){} , ()=>{} this를 바인딩하기 위해서!!
			this.upateBoard();
		});
		/*$("#btn-login").on("click", () => { //function(){} , ()=>{} this를 바인딩하기 위해서!!
			this.login();
		});*/
	},

	save: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		};
		console.log(data);
		//ajax호출시 default = 비동기
		$.ajax({
			//회원가입 수행 요청
			type:"POST",
			url:"/api/board",
			data:JSON.stringify(data), //http body 데이터
			contentType:"application/json;charset=utf-8", //body 데이터 타입(MIME)
			dataType:"json" //응답 타입 , 기본적으로 문자열이나 json 형태면 json형식으로 응답
		}).done(function(resp) {
			alert("글쓰기가  완료되었습니다.");
			//console.log(resp);
			location.href = "/";
		}).fail(function(error) {
			alert(JSON,stringify(error));
		});
	},
	
	deleteById: function() {
		let id = $("#id").text();
		$.ajax({
			type:"DELETE",
			url:"/api/board/"+id,
			dataType:"json" //응답 타입 , 기본적으로 문자열이나 json 형태면 json형식으로 응답
		}).done(function(resp) {
			alert("삭제가  완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON,stringify(error));
		});
	},
	
	upateBoard: function() {
		let id = $("#id").val();
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		};
		//ajax호출시 default = 비동기
		$.ajax({
			//회원가입 수행 요청
			type:"PUT",
			url:"/api/board/"+id,
			data:JSON.stringify(data), //http body 데이터
			contentType:"application/json;charset=utf-8", //body 데이터 타입(MIME)
			dataType:"json" //응답 타입 , 기본적으로 문자열이나 json 형태면 json형식으로 응답
		}).done(function(resp) {
			alert("글수정  완료되었습니다.");
			//console.log(resp);
			location.href = "/";
		}).fail(function(error) {
			alert(JSON,stringify(error));
		});
	},
}

index.init();