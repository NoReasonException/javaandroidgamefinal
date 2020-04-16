from django.http import HttpResponse
import json

a={"Stef":"12:03"}

def getScores(request):

    return HttpResponse(json.dumps(a))


def postScore(request):
    name=request.GET.get('name', '')
    time=request.GET.get('time', '')
    a[name]=time[:2]+":"+time[2:]
    return HttpResponse(json.dumps(a))