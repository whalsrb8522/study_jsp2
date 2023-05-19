// 화면에서 등록한 댓글내용을 컨트롤러로 전송 -> DB저장
async function postCommentToServer(cmtData) {
	try {
		const uri = "/cmt/post";
		const config = {
			method : 'post',
			headers : {
				'content-Type':'application/json; charset=UTF-8'
			},
			body : JSON.stringify(cmtData)
		};
		const resp = await fetch(uri, config);
		const result = await resp.text();	// isOk
		return result;
	} catch(error) {
		console.log(error);
	}
}

// 미리 보낼 데이터를 만들어서 함수로 전달 cmtData
document.getElementById('cmtAddBtn').addEventListener('click', () => {
	const cmtText = document.getElementById('cmtText').value;
	console.log(cmtText);
	if (cmtText == null || cmtText == "") {
		alert('댓글을 입력해주세요.');
		return false;
	} else {
		let cmtData = {
			bno : bnoVal,
			writer : document.getElementById('cmtWriter').value,
			content : cmtText
		};
		postCommentToServer(cmtData).then(result => {
			if(result > 0) {
				alert('댓글 등록 정상');
				document.getElementById('cmtText').value = '';
			}
		});
	}
});

async function getCommentListFromServer(bno) {
	try {
		const resp = await fetch('/cmt/list/' + bno);
		const cmtList = await resp.json();	// 댓글 객체가 리턴
		return cmtList;
	} catch (error) {
		console.log(error);
	}
}

getCommentListFromServer(bnoVal).then(result => {
	console.log(result);
});

function spreadCommentList(result) {

}