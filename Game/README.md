WARNING , this game has only been tested and written for Pixel 3a

additionally ,in order to run you need to perform some actions

1) start the given server , by running at following

'''shell
cd repo/server
python3 manage.py runserver 0.0.0.0:8000
'''

and then you need to make ifconfig and find you internal IP address , let you address be 192.168.1.9 then

you need to change the BASE_URL static variable at HttpUtils as follows

'''java

public class HttpUtils {
    private static final String BASE_URL = "http://192.168.1.9:8000/";


'''

