node = 0
root = {}
ch = {}
son = {}


def build(id, words, alphaset):
    M = 0
    tch = 'a'
    for x in range(ord('a'), ord('z') + 1):
        c = chr(x)
        if c in alphaset:
            continue
        cnt = 0
        for w in words:
            if c in w:
                cnt += 1
        if cnt > M:
            M = cnt
            tch = c
    if M == 0:
        return
    ch[id] = tch
    alphaset = alphaset + (tch, )
    maskmap = {}
    for w in words:
        mask = 0
        l = len(w)
        for i in range(l):
            if w[i] in alphaset:
                mask += 1 << i
        if mask not in maskmap:
            maskmap[mask] = []
        maskmap[mask].append(w)
    son[id] = {}
    for key, value in maskmap.items():
        global node
        node += 1
        son[id][key] = node
        build(node, value, alphaset)
    return


wordlist = []
f = open('wordlist.txt', 'r')
for i in f:
    wordlist.append(i[:-1] if i[-1] == '\n' else i)
wordbylen = {}
for w in wordlist:
    l = len(w)
    if l not in wordbylen:
        wordbylen[l] = []
    wordbylen[l].append(w)
for key, value in wordbylen.items():
    node += 1
    root[key] = node
    build(node, value, ())


lu = 0
cur = 0
err = 0
while True:
    I = input()
    if I == 'END':
        break
    l = len(I)
    u = 0
    for i in range(l):
        if I[i] == '_':
            u += 1
    if u == 0:
        lu = 0
        continue
    if lu == 0:
        err = 0
        cur = root[l]
    else:
        if lu == u:
            err += 1
            if err == 6:
                lu = 0
                continue
        mask = 0
        for i in range(l):
            if I[i] is not '_':
                mask += 1 << i
        cur = son[cur][mask]
    print(ch[cur])
    lu = u