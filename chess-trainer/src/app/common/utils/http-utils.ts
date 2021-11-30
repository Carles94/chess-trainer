import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { DeleteMoveBody } from '../model/interface/delete-move-body';
import { PostMoveBody } from '../model/interface/post-move-body';

export class HttpUtils {
  static getPosition(lineUuid: string, fenPosition: string, http: HttpClient): Observable<Object> {
    return http.request('GET', `${environment.apiUrl}/position/${lineUuid}/${fenPosition}`, {
      responseType: 'json',
    });
  }

  static postMoveEvent(body: PostMoveBody, http: HttpClient): Observable<Object> {
    return http.post(`${environment.apiUrl}/move`, body, {
      responseType: 'json',
    });
  }

  static deleteMove(body: DeleteMoveBody, http: HttpClient): Observable<Object> {
    return http.request('DELETE', `${environment.apiUrl}/move`, {
      responseType: 'json',
      body: body,
    });
  }
}
