from flask import *
from database import *
public=Blueprint('public',__name__)

@public.route('/',methods=['post','get'])
def login():
	if 'submit' in request.form:
		user=request.form['username']
		pss=request.form['password']
		q="select * from login_table where username='%s' and password='%s'"%(user,pss)
		res=select(q)
		print(res)
		if res:
			session['logid']=res[0]['log_id']
			if res[0]['type']=='admin':
				return redirect(url_for('admin.adminhome'))
			elif res[0]['type']=='salesteam':
				return redirect(url_for('salesteam.saleshome')) 
			elif res[0]['type']=='logistics':
				return redirect(url_for('logistics.logisticshome'))
	return render_template('index.html')
