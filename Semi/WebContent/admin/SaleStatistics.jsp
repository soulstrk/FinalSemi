<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="ljy.vo.OrderVo1"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<link href="css/saleStatistics.css?ver=6" rel="stylesheet">
</head>
<div class="container-fluid" style="padding-top: 150px;">
	<div class="row">
		<div class="col-md-12 orderHistory" style="text-align: center;"><span>Sales Statistic</span></div>
	</div>
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-10">
		<form action="adminProduct.do?adminNum=2" method="post">
			<select name="yearSel" id="yearSel" onchange="monthSel()">
				<option value="18" selected="selected">2018</option>
				<option value="17">2017</option>
			</select>
			<select name="monthSel" id="monthSel" onchange="monthSel()">
				<option value="a" selected="selected">선택</option>
				<option value="01">1월</option>
				<option value="02">2월</option>
				<option value="03">3월</option>
				<option value="04">4월</option>
				<option value="05">5월</option>
				<option value="06">6월</option>
				<option value="07">7월</option>
				<option value="08">8월</option>
				<option value="09">9월</option>
				<option value="10">10월</option>
				<option value="11">11월</option>
				<option value="12">12월</option>
			</select>
			<button type="submit" id="staticBtn"><img src="images/graph.png" alt="" /></button>
			</form>
		</div>
		<div class="col-md-2"></div>
	</div>
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<table class="table table-striped" id="mTable">
				<tr id="sTable">
					<th>날짜</th>
					<th>상품판매총액</th>
					<th>상품판매량</th>
					<th>일일 상품판매액 그래프</th>
				</tr>
<%
	int nextDay = 1;
		int sumPay = 0;
		int sumSale = 0;
		int maxPay = 0;
	if(request.getAttribute("list") != null){
	ArrayList<OrderVo1> list = new ArrayList<>();
	list = (ArrayList<OrderVo1>)request.getAttribute("list");
	int month = Integer.parseInt((String)request.getAttribute("month"));
	int allSumPay = 0;
	int allSalePay = 0;
		for(int i = 0; i<list.size();){
			OrderVo1 vo = list.get(i);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String sday = df.format(vo.getoDate());
			String ssday = sday.substring(sday.length()-2);
			int day = Integer.parseInt(ssday);
			if(nextDay == day){
				sumPay += vo.getoPayment();
				sumSale += vo.getoAmount();
				i++;
			}else{
				if(maxPay <= sumPay){
					maxPay = sumPay;
				}
				allSumPay += sumPay;
				allSalePay += sumSale;
%>
				<tr>
					<td><%=month %>월<%=nextDay %>일</td>
					<td><span class="static<%=nextDay%>"><%= sumPay %></span> 원</td>
					<td><%= sumSale %></td>
					<td>
					<div class="progress" style="height: 30px;"><div class="progress-bar progress-bar-striped progress-bar-animated bar<%=nextDay%>" style="width:0%; height: 40px;"></div></div>	
					</td>
				</tr>
<%						
						nextDay++;
						sumPay = 0;
						sumSale = 0;
					}
				}
				if(maxPay <= sumPay){
					maxPay = sumPay;
				}
				allSumPay += sumPay;
				allSalePay += sumSale;
%>
				<tr>
					<td><%=month %>월<%=nextDay %>일</td>
					<td><span class="static<%=nextDay%>"><%= sumPay %></span> 원</td>
					<td><%= sumSale %></td>
					<td>
					<div class="progress" style="height: 30px;"><div class="progress-bar progress-bar-striped progress-bar-animated bar<%=nextDay%>" style="width:0%; height: 40px;"></div></div>
					</td>
				</tr>
				<tr style="color : red;">
					<td><%=month %>월</td>
					<td class="static<%=nextDay%>">상품총액</td>
					<td><%=allSumPay %>원</td>
					<td>총 상품 판매량 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <%=allSalePay %>개</td>
				</tr>
<%
	}
%>
			</table>
		</div>
	</div>
	<div class="col-md-2"></div>
</div>

<script type="text/javascript">
	var maxNum = <%= maxPay %>;
	$(document).ready(function() {
		for (var i = 1; i <= <%=nextDay%>; i++) {
			var pay = $('.static'+i+'').text();
			var result = pay/maxNum*100;
			var finalPay = Math.round(result);
			if(finalPay == 100){
				var tmp = $('.static'+i+'').text();
				$('.static'+i+'').css('color','blue').css('font-weight','bold').html('<span class="badge badge-primary">'+tmp+'</span>');
				$('.badge-primary').css('height','35px').css('font-size','25px');
				}
			$('.bar'+i+'').css('width',finalPay+'%');
		}
	})
	
</script>
