from flask import *
from database import *
import uuid
salesteam=Blueprint('salesteam',__name__)
@salesteam.route('/saleshome',methods=['post','get'])
def saleshome():
	return render_template("saleshome.html")

@salesteam.route('/conforders',methods=['post','get'])
def conforders():
	data={}
	if 'action' in request.args:
		action=request.args['action']
		id=request.args['id']
	else:
		action=None
	if action=="confirm":
		q="update order_table set order_status='confirmed' where order_id='%s' and order_status='requested'"%(id)
		update(q)
	q="SELECT *,CONCAT(`cust_fname`,' ',`cust_lname`)AS NAME FROM `order_table` INNER JOIN `customers_table` USING(`cust_id`) INNER JOIN  `products_table` USING(`pro_id`) where order_table.order_status!='cart'"
	res=select(q)
	data['view']=res
	return render_template("salesvieworders.html",data=data) 

@salesteam.route('/confpayment',methods=['get','post'])
def confpayment():
	data={}
	if 'action' in request.args:
		action=request.args['action']
		payid=request.args['payid']
		orderid=request.args['orderid']
	else:
		action=None
	if action=="confirm":
		q="update payment_table set pay_status='confirmed' where pay_id='%s' and pay_status='pending'"%(payid)
		update(q)
		q="insert into sales_table values(null,(SELECT `sales_id` FROM `salesteam_table` WHERE `log_id`='%s'),'%s',curdate(),'paid')"%(session['logid'],orderid)
		id=insert(q)
		if id:
			flash('confirmed successfully !!!')
	q="SELECT *,CONCAT(`cust_fname`,' ',`cust_lname`)AS NAME FROM `payment_table` INNER JOIN `customers_table` USING(`cust_id`) INNER JOIN  `products_table` USING(`pro_id`) INNER JOIN `order_table` USING(`order_id`) where order_table.order_status!='cart'"
	res=select(q)
	data['view']=res
	return render_template("salesconfpayment.html",data=data) 
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
@salesteam.route('/logout')
def logout():
	session.clear()
	return redirect(url_for('public.login'))
