from flask import *
from database import *
from public import public
from admin import admin
from salesteam import salesteam
from logistics import logistics
from api import api 

app=Flask(__name__)
app.secret_key="fitandmatch"

app.register_blueprint(public)
app.register_blueprint(admin,url_prefix='/admin')
app.register_blueprint(salesteam,url_prefix='/salesteam')
app.register_blueprint(logistics,url_prefix='/logistics')
app.register_blueprint(api,url_prefix='/api')

app.run(debug="true",port=5009,host="0.0.0.0")

