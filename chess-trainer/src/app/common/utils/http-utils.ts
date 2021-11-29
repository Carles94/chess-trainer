import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DeleteMoveBody } from '../model/interface/delete-move-body';
import { Position } from '../model/interface/position';
import { PostMoveBody } from '../model/interface/post-move-body';

export class HttpUtils {
  static getPosition(lineUuid: string, fenPosition: string, http: HttpClient): Observable<Object> {
    return http.request('GET', `http://localhost:8080/chess-trainer/position/${lineUuid}/${fenPosition}`, {
      responseType: 'json',
    });
  }

  static postMoveEvent(body: PostMoveBody, http: HttpClient): Observable<Object> {
    return http.post('http://localhost:8080/chess-trainer/move', body, {
      responseType: 'json',
    });
  }

  static deleteMove(body: DeleteMoveBody, http: HttpClient): Observable<Object> {
    return http.request('DELETE', 'http://localhost:8080/chess-trainer/move', {
      responseType: 'json',
      body: body,
    });
  }
}
