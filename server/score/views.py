from django.http import HttpResponse
import json

a=[]

def transformToClockTime(k,v):
    return {"name":k,"time": v[:2] + ":" + v[2:]}
    #return {k:v[:2] + ":" + v[2:]}
def transformWholeArrayToClockTime(a):
    b=[]
    for i in a:
        for k,v in i.items():#This will run only once ;)
            b.append(transformToClockTime(k,v))
    return b

def criteria(pair):
    return int(list(pair.values())[0])
def getScores(request):
    a.sort(key=criteria,reverse=True)
    return HttpResponse("{'results':"+json.dumps(transformWholeArrayToClockTime(a))+"}")
#time[:2] + ":" + time[2:]

def postScore(request):
    name=request.GET.get('name', '')
    time=request.GET.get('time', '')
    a.append({name:time})
    a.sort(key=criteria,reverse=True)

    return HttpResponse("{'results':"+json.dumps(transformWholeArrayToClockTime(a))+"}")

def clearScores(request):
    global a
    a=[]
    return HttpResponse("{'results':"+json.dumps(transformWholeArrayToClockTime(a))+"}")